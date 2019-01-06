//package com.ald.cloud.consumer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
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
//  * @Date 13:36 2017/11/9
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//@Service
//public class HelloService {
//
////    @Autowired
////    RestTemplate restTemplate;
//
//    public String hiService(String phone) {
//        return restTemplate.getForObject("http://TEST/select/"+phone,String.class);
//    }
//
//}