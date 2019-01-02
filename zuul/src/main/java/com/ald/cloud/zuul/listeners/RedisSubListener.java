//package com.ald.cloud.zuul.listeners;
//
//import com.ald.cloud.zuul.domain.FilterConfigDo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.stereotype.Component;
//import org.springframework.util.ObjectUtils;
//
//import javax.annotation.Resource;
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
//  * @author luohao
//  * @version 1.0.0
//  * @date 18:00 2017/12/1
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//@Component
//public class RedisSubListener{
//
//    private Logger logger = LoggerFactory.getLogger(RedisSubListener.class);
//
//    @Resource
//    CacheManager cacheManager;
//
//    @Resource
//    Jackson2JsonRedisSerializer<FilterConfigDo> serializer;
//
//    public void receive(String message) {
//        logger.info("receive message from redis , message is '{}'", message);
//        if(ObjectUtils.nullSafeEquals(message, "flushCode")){
//            logger.info("cache flush started...");
//            Long past = System.currentTimeMillis();
//            Cache cache = cacheManager.getCache("interfaceCache");
//            //当收到信息时, 刷新缓存
//            cache.clear();
//            Long curr = System.currentTimeMillis();
//            logger.info("cache flush completed, cost {} milliseconds", curr-past);
//        }
//    }
//
//}
