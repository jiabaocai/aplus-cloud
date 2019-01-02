//package com.ald.cloud.zuul.filters;
//
//import com.ald.cloud.zuul.Utils.JwtTokenUtil;
//import com.ald.cloud.zuul.Utils.StringUtil;
//import com.ald.cloud.zuul.base.AbstractFilter;
//import com.ald.cloud.zuul.constants.Constants;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//import javax.servlet.ServletInputStream;
//
///**
//  *                  ,___         .-;'
//  *                  `"-.`\_...._/`.`
//  *             ,       \        /
//  *         .-' ',      / ()   ()\
//  *         `'._ \     /()    .  (|
//  *             > .'  ;,     -'-  /
//  *            / <   |;,     __.;
//  *            '-.'-.|  , \    , \
//  *               `>.|;, \_)    \_)
//  *                `-;     ,    /
//  *                  \    /   <
//  *                  '. <`'-, _)
//  *                  '._)
//  *
//  * @Author luohao
//  * @Version 1.0.0
//  * @Date 14:19 2017/11/29
//  * @Desription token过滤器, JWTtoken
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//public class TokenFilter extends AbstractFilter {
//
//    private Logger logger = LoggerFactory.getLogger(getClassName());
//
//    @Override
//    public String filterType() {
//        return Constants.FILTER_TYPE_PRE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return Constants.FILTER_TOKEN;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return this.setConfigurationIfNessary(filterOrder());
//    }
//
//    @Override
//    public Object run() {
//
//        String url = this.getUrl();
//
//        if(this.getException() != null){
//            logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, this.getException());
//            this.processWhenFailed(Constants.ERR_CODE, Constants.ERR_MSG);
//        }else{
//            boolean tokenConfirmed = false;
//
//            try {
//                tokenConfirmed = this.check();
//            } catch (Exception e) {
//                logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, e);
//            }
//            if(!tokenConfirmed){
//                this.processWhenFailed(Constants.FORBIDDEN_RSA, "access denied for token checkout failure.");
//
//            }
//            logger.info(tokenConfirmed? Constants.LOG_FILTER_NEXT : Constants.LOG_FILTER_DINED
//                    , this.getClassName(), url);
//        }
//        return null;
//    }
//
//    /**
//     * 检查token是否存在
//     * @return
//     */
//    private boolean check(){
//        String token = getToken();
//        return !StringUtil.isEmpty(token) && null != JwtTokenUtil.getToken(token, Constants.SECRET_TOKEN);
//    }
//
//    /**
//     * 规定token存放在header中
//     * 后续添加代码从各方面获取token
//     * @return
//     */
//    private String getToken(){
////        HttpServletRequest request = this.getRequest();
////        String token = request.getHeader(Constants.KEY_TOKEN);
////        if(StringUtil.isEmpty(token)){
////            token = request.getParameter(Constants.KEY_TOKEN);
////            if(StringUtil.isEmpty(token)){
////                String payload = requestPayload();
////                if(StringUtil.isNotEmpty(payload)){
////                    JSONObject json = JSONObject.parseObject(payload);
////                    token = json.containsKey(Constants.KEY_TOKEN)? (String)json.get(Constants.KEY_TOKEN):null;
////                }
////            }
////        }
//        return this.getRequest().getHeader(Constants.KEY_TOKEN);
//    }
//
//    private String requestPayload(){
//            ServletInputStream is;
//            try {
//                is = this.getRequest().getInputStream();
//                int nRead = 1;
//                int nTotalRead = 0;
//                byte[] bytes = new byte[10240];
//                while (nRead > 0) {
//                    nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
//                    if (nRead > 0)
//                        nTotalRead = nTotalRead + nRead;
//                }
//                String str = new String(bytes, 0, nTotalRead, "utf-8");
//                return str;
//            } catch (IOException e) {
//                logger.error("an exception occurred while getting params from payload", e);
//                return "";
//            }
//    }
//}
