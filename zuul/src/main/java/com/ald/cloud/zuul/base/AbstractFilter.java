package com.ald.cloud.zuul.base;

import com.ald.cloud.zuul.constants.Constants;
import com.ald.cloud.zuul.services.ConfigService;
import com.ald.cloud.zuul.Utils.IpUtils;
import com.ald.cloud.zuul.domain.FilterConfigDo;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ExecutionStatus;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.monitoring.Tracer;
import com.netflix.zuul.monitoring.TracerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
  *                  ,___         .-;'
  *                  `"-.`\_...._/`.`
  *             ,       \        /
  *         .-' ',      / ()   ()\
  *         `'._ \     /()    .  (|
  *             > .'  ;,     -'-  /
  *            / <   |;,     __.;
  *            '-.'-.|  , \    , \
  *               `>.|;, \_)    \_)
  *                `-;     ,    /
  *                  \    /   <
  *                  '. <`'-, _)
  *                  '._)
  * 
  * @Author luohao
  * @Version 1.0.0
  * @Date 13:39 2017/12/1
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
 
public abstract class AbstractFilter extends ZuulFilter{

    private Logger logger = LoggerFactory.getLogger(getClassName());

    @Resource
    public ConfigService configService;


    /**
     * 复写父类方法, 加了一个锁
     * 防止父类中的  config和exception属性在并发的时候被修改
     * 如果有更好的办法和设计, 以后会尝试改进
     * @return
     */
    @Override
    public synchronized ZuulFilterResult runFilter() {
        ZuulFilterResult zr = new ZuulFilterResult();
        if (!isFilterDisabled()) {
           if (shouldFilter()) {
               Tracer t = TracerFactory.instance().startMicroTracer("ZUUL::" + this.getClass().getSimpleName());
               try {
                   Object res = run();
                   zr = new ZuulFilterResult(res, ExecutionStatus.SUCCESS);
               } catch (Throwable e) {
                   t.setName("ZUUL::" + this.getClass().getSimpleName() + " failed");
                   zr = new ZuulFilterResult(ExecutionStatus.FAILED);
                   zr.setException(e);
               } finally {
                   t.stopAndLog();
               }
           } else {
               zr = new ZuulFilterResult(ExecutionStatus.SKIPPED);
           }
        }
        return zr;

    }

    /**
     * 配置信息
     */
    private FilterConfigDo config;

    /**
     * 异常
     */
    private Throwable exception;


    /**
     * 验证失败后的逻辑
     */
    public void processWhenFailed(Integer code, String msg){
        RequestContext ctx = RequestContext.getCurrentContext();

        //没有被写入的才进行处理
        if( ctx.getResponse().getStatus() == Constants.DEFAULT_RESPONSE_STATUS ){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(code);
            Map<String, Object> resMap = new HashMap<>();
            resMap.put("code", code);
            resMap.put("msg", msg);
            try {
                ctx.getResponse().getWriter().write(JSONObject.toJSONString(resMap));
            }catch (Exception e){
                //ignore
            }
        }
    }

    /**
     * 如果需要拦截, 设置拦截规则
     * @return
     */
    public boolean setConfigurationIfNessary(int filterOrder){
        boolean shouldFilter = this.getResponse().getStatus() == Constants.DEFAULT_RESPONSE_STATUS;

        //如果前置拦截做了处理, 不进行拦截
        if(shouldFilter){
            String url = this.getUrl();

            List<FilterConfigDo> configList = null;
            try {
                configList = this.configService.get(url);

                for(FilterConfigDo config : configList){
                    if(ObjectUtils.nullSafeEquals(config.getConfirmRule(), filterOrder)){
                        logger.info(Constants.LOG_FILTER_STARTED, this.getClassName(), url );
                        this.setConfig(config);
                        break;
                    }
                }
                if(null == this.getConfig()){
                    shouldFilter = false;
                }

            } catch (Exception e) {
                //查询发生异常后由具体的拦截逻辑处理
                this.setException(e);
            }
        }
        return shouldFilter;
    }

    /**
     * 获取验证规则
     * @return
     */
    public FilterConfigDo getConfig() {
        return config;
    }

    /**
     * 设置验证规则
     * @param config
     */
    public void setConfig(FilterConfigDo config) {
        this.config = config;
    }

    /**
     * 获取上下文
     * @return
     */
    public RequestContext getCtx() {
        return RequestContext.getCurrentContext();
    }

    /**
     * 获取request
     * @return
     */
    public HttpServletRequest getRequest() {
        return getCtx().getRequest();
    }

    /**
     * 获取response
     * @return
     */
    public HttpServletResponse getResponse() {
        return getCtx().getResponse();
    }

    /**
     * 获取url
     * @return
     */
    public String getUrl(){
        return getRequest().getRequestURI();
    }

    /**
     * 获取类名
     * @return
     */
    public String getClassName(){
        return this.getClass().getName();
    }

    /**
     * 获取请求IP
     * @return
     */
    public String getIpAddr(){
        return IpUtils.getRemoteHost(getRequest());
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
