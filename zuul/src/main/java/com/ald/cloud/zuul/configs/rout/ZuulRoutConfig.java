//package com.ald.cloud.zuul.configs.rout;
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
//  * @Date 15:30 2017/12/5
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//import com.ald.cloud.zuul.services.ZuulRoutService;
//
//import org.springframework.boot.autoconfigure.web.ServerProperties;
//import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class ZuulRoutConfig {
//
//    @Resource
//    ZuulProperties zuulProperties;
//    @Resource
//    ServerProperties server;
//    @Resource
//    ZuulRoutService zuulRoutService;
//
//    @Bean
//    public ZuulRoutHelper routeLocator() {
//        return new ZuulRoutHelper(this.server.getServletPrefix(), this.zuulProperties, this.zuulRoutService);
//    }
//
//}