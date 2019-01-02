package com.ald.cloud.zuul.services.impl;

import com.ald.cloud.zuul.services.ConfigService;
import com.ald.cloud.zuul.domain.FilterConfigDo;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
  * @Date 18:06 2017/11/29
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */

@Service
@Transactional
@CacheConfig(cacheNames = "interfaceCache")
public class ConfigServiceImpl implements ConfigService {

    @Resource
    private RedisTemplate redisTemplate;

    @Cacheable(key = "''+#url")
    @Override
    public List<FilterConfigDo> get(String url) {
        System.out.println("Cacheable");
        return redisTemplate.opsForList().range(url, 0, -1);
    }

    @Override
    public void addListCache(String url, FilterConfigDo config) {
        redisTemplate.opsForList().leftPush(url, config);
    }


}
