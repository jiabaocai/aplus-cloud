//package com.ald.cloud.zuul.domain;
//
//import java.io.Serializable;
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
//  * @Date 15:40 2017/12/5
//  * @Desription 路由配置实体
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//public class ZuulRouteDo implements Serializable {
//
//    private String id;
//
//    private String path;
//
//    private String serviceId;
//
//    private String url;
//
//    private boolean stripPrefix = true;
//
//    private Boolean retryable;
//
//    private Boolean enabled;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public String getServiceId() {
//        return serviceId;
//    }
//
//    public void setServiceId(String serviceId) {
//        this.serviceId = serviceId;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public boolean isStripPrefix() {
//        return stripPrefix;
//    }
//
//    public void setStripPrefix(boolean stripPrefix) {
//        this.stripPrefix = stripPrefix;
//    }
//
//    public Boolean getRetryable() {
//        return retryable;
//    }
//
//    public void setRetryable(Boolean retryable) {
//        this.retryable = retryable;
//    }
//
//    public Boolean getEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(Boolean enabled) {
//        this.enabled = enabled;
//    }
//}