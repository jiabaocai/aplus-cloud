//package com.ald.cloud.zuul.filters;
//
//import com.ald.cloud.zuul.Utils.StringUtil;
//import com.ald.cloud.zuul.Utils.rsa.SignUtil;
//import com.ald.cloud.zuul.base.AbstractFilter;
//import com.ald.cloud.zuul.constants.Constants;
//import com.ald.cloud.zuul.domain.FilterConfigDo;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.http.HttpServletRequest;
//
//import java.util.Collections;
//import java.util.List;
//
//
///**
// *                  ,___         .-;'
// *                  `"-.`\_...._/`.`
// *             ,       \        /
// *         .-' ',      / ()   ()\
// *         `'._ \     /()    .  (|
// *             > .'  ;,     -'-  /
// *            / <   |;,     __.;
// *            '-.'-.|  , \    , \
// *               `>.|;, \_)    \_)
// *                `-;     ,    /
// *                  \    /   <
// *                  '. <`'-, _)
// *                  '._)
// *
// * @Author luohao
// * @Version 1.0.0
// * @Date 19:18 2017/11/30
// * @Desription RSA过滤器
// * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
// */
//public class RsaFilter extends AbstractFilter {
//    private Logger logger = LoggerFactory.getLogger(this.getClassName());
//
//    @Override
//    public String filterType() {
//        return Constants.FILTER_TYPE_PRE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return Constants.FILTER_RSA;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return this.setConfigurationIfNessary(filterOrder());
//    }
//
//    @Override
//    public Object run() {
//        String url = this.getUrl();
//
//        if(this.getException() != null){
//            logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, this.getException());
//            this.processWhenFailed(Constants.ERR_CODE, Constants.ERR_MSG);
//        }else{
//            boolean RsaConfirmed = false;
//
//            try {
//                RsaConfirmed = this.check();
//            } catch (Exception e) {
//                logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, e);
//            }
//            if(!RsaConfirmed){
//                this.processWhenFailed(Constants.FORBIDDEN_RSA, "access denied for RSA checkout failure.");
//
//            }
//            logger.info(RsaConfirmed? Constants.LOG_FILTER_NEXT : Constants.LOG_FILTER_DINED
//                    , this.getClassName(), url);
//        }
//        return null;
//    }
//
//    /**
//     * RSA验签
//     * @return
//     */
//    private boolean check()  {
//        FilterConfigDo config = this.getConfig();
//        List<String> paramKeys = config.getList();
//        if(CollectionUtils.isEmpty(paramKeys)){
//            return false;
//        }
//        String keyColumn = config.getKeyColumn();
//        if(StringUtil.isEmpty(keyColumn)){
//            return false;
//        }
//        Collections.sort(paramKeys);
//
//        HttpServletRequest request = this.getRequest();
//
//        if(!request.getParameterMap().containsKey(keyColumn)){
//            return false;
//        }
//
//        String sign = request.getParameter(keyColumn);
//        if(StringUtil.isEmpty(sign)){
//            return false;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < paramKeys.size(); i++) {
//            String key = paramKeys.get(i);
//            if(request.getParameterMap().containsKey(key)){
//                String value = this.getRequest().getParameter(key);
//                sb.append(key);
//                sb.append("=");
//                sb.append(value);
//                if (i < paramKeys.size() - 1)
//                    sb.append("&");
//            }else{
//                return false;
//            }
//        }
//
//        return SignUtil.checkSign(sb.toString(), sign, Constants.PUBLIC_KEY);
//    }
//}
