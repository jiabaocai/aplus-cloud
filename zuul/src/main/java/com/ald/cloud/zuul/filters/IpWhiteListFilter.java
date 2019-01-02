package com.ald.cloud.zuul.filters;

import com.ald.cloud.zuul.base.AbstractFilter;
import com.ald.cloud.zuul.constants.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

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
  * @Date 19:18 2017/11/30
  * @Desription ip白名单过滤器
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
public class IpWhiteListFilter extends AbstractFilter{

    private Logger logger = LoggerFactory.getLogger(IpWhiteListFilter.class);

    @Override
    public String filterType() {
        return Constants.FILTER_TYPE_PRE;
    }

    @Override
    public int filterOrder() {
        return Constants.FILTER_WHITE;
    }

    /**
     * 接口只允许名单内的访问
     * @return
     */
    @Override
    public boolean shouldFilter() {

        return this.setConfigurationIfNessary(filterOrder());

    }

    @Override
    public Object run() {

        String url = this.getUrl();

        if(this.getException() != null){
            logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, this.getException());
            this.processWhenFailed(Constants.ERR_CODE, Constants.ERR_MSG);
        }else{
            //白名单过滤
            boolean accessAble = false;
            List<String> ipWhiteList = this.getConfig().getList();
            if (!CollectionUtils.isEmpty(ipWhiteList) && ipWhiteList.contains(this.getIpAddr())) {
                accessAble = true;
            }
            if(!accessAble){
                //不通过
                logger.info(Constants.LOG_FILTER_DINED, this.getClassName(), url);
                this.processWhenFailed(Constants.FORBIDDEN_IP_WHITE, "access denied for host not in whiteList");
            }else{
                //通过
                logger.info(Constants.LOG_FILTER_NEXT, this.getClassName(), url);
            }
        }

        return null;
    }
}
