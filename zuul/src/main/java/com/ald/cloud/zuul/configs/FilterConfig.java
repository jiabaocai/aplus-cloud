//package com.ald.cloud.zuul.configs;
//
//import com.ald.cloud.zuul.filters.IpBlackListFilter;
//import com.ald.cloud.zuul.filters.IpWhiteListFilter;
//import com.ald.cloud.zuul.filters.Md5Filter;
//import com.ald.cloud.zuul.filters.RsaFilter;
//import com.ald.cloud.zuul.filters.TokenFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
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
//  * @Date 11:39 2017/12/5
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//@Configuration
//public class FilterConfig {
//    @Bean
//    public IpBlackListFilter ipBlackListFilter() {
//        return new IpBlackListFilter();
//    }
//
//    @Bean
//    public IpWhiteListFilter ipWhiteListFilter(){
//        return new IpWhiteListFilter();
//    }
//
//    @Bean
//    public Md5Filter md5Filter() {
//        return new Md5Filter();
//    }
//
//    @Bean
//    public RsaFilter rsaFilter() {
//        return new RsaFilter();
//    }
//
//    @Bean
//    public TokenFilter tokenFilter() {
//        return new TokenFilter();
//    }
//
//}
