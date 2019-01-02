//package com.ald.cloud.zuul.services.impl;
//
//import com.ald.cloud.zuul.services.ZuulRoutService;
//import com.ald.cloud.zuul.domain.ZuulRouteDo;
//
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.List;
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
//  * @Date 15:46 2017/12/5
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//@Service
//@Transactional
//@CacheConfig(cacheNames = "zuulRouts")
//public class ZuulRoutServiceImpl implements ZuulRoutService {
//
//    @Resource
//    private RedisTemplate redisTemplate;
//
//    @Cacheable(key = "'routs'")
//    @Override
//    public List<ZuulRouteDo> listRouts() {
//        return redisTemplate.opsForList().range("routs", 0, -1);
//    }
//
//    @Override
//    public void addRout(ZuulRouteDo rout) {
//        redisTemplate.opsForList().leftPush("routs", rout);
//    }
//}
