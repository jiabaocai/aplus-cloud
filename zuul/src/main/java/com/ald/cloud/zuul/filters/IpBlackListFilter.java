package com.ald.cloud.zuul.filters;

import com.ald.cloud.zuul.base.AbstractFilter;
import com.ald.cloud.zuul.constants.Constants;
import com.ald.cloud.zuul.domain.FilterConfigDo;
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
  * @Date 15:06 2017/11/29
  * @Desription IP黑名单过滤器
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
public class IpBlackListFilter  extends AbstractFilter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public String filterType() {
        return Constants.FILTER_TYPE_PRE;
    }

    @Override
    public int filterOrder() {
        return Constants.FILTER_BLACK;
    }

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

            FilterConfigDo config = this.getConfig();
            List<String> ipList = config.getList();

            if(!CollectionUtils.isEmpty(ipList) && ipList.contains(this.getIpAddr())){
                //黑名单, 禁止通行
                logger.info(Constants.LOG_FILTER_DINED, this.getClassName(), url);
                this.processWhenFailed(Constants.FORBIDDEN_IP_BLACK, "access denied for host in blackList.");
            }else{
                //通行日志
                logger.info(Constants.LOG_FILTER_NEXT, this.getClassName(), url);
            }
        }

        return null;
    }

}
