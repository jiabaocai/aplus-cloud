package com.ald.cloud.zuul.filters;

import com.ald.cloud.zuul.Utils.DigestUtil;
import com.ald.cloud.zuul.base.AbstractFilter;
import com.ald.cloud.zuul.constants.Constants;
import com.ald.cloud.zuul.domain.FilterConfigDo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
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
  * @Desription MD5签名过滤器
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */

public class Md5Filter extends AbstractFilter{
    private Logger logger = LoggerFactory.getLogger(this.getClassName());

    @Override
    public String filterType() {
        return Constants.FILTER_TYPE_PRE;
    }

    @Override
    public int filterOrder() {
        return Constants.FILTER_MD5;
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
            boolean md5Confirmed = false;

            try {
                md5Confirmed = this.check();
            } catch (Exception e) {
                logger.error(Constants.LOG_FILTER_ERROR, this.getClassName(), url, e);
            }
            if(!md5Confirmed){
                this.processWhenFailed(Constants.FORBIDDEN_MD5, "access denied for MD5 checkout failure.");

            }
            logger.info(md5Confirmed? Constants.LOG_FILTER_NEXT : Constants.LOG_FILTER_DINED
                        , this.getClassName(), url);
        }

        return null;
    }

    /**
     * MD5检查, 键将会ascii排序
     * @return
     */
    private boolean check() throws UnsupportedEncodingException {
        FilterConfigDo config = this.getConfig();
        String keyColumn = config.getKeyColumn();
        if(StringUtils.isEmpty(keyColumn)){
            return false;
        }

        List<String> params = config.getList();
        if(CollectionUtils.isEmpty(params)){
            return false;
        }
        Collections.sort(params);

        HttpServletRequest request = this.getRequest();

        String salt = config.getKey();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            String key = params.get(i);
            if(request.getParameterMap().containsKey(key)){
                String value = this.getRequest().getParameter(key);
                sb.append(key);
                sb.append("=");
                sb.append(value);
                if (i < params.size() - 1)
                    sb.append("&");
            }else{
                return false;
            }

        }

        byte[] saltBytes = DigestUtil.decodeHex(salt);
        byte[] curPwdBytes = DigestUtil.digestString(sb.toString().getBytes("UTF-8"), saltBytes,
                DigestUtil.DEFAULT_DIGEST_TIMES, DigestUtil.SHA1);

        return ObjectUtils.nullSafeEquals(DigestUtil.encodeHex(curPwdBytes), request.getParameter(keyColumn));
    }
}
