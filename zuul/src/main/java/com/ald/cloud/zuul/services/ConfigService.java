package com.ald.cloud.zuul.services;

import com.ald.cloud.zuul.base.BaseFilterService;
import com.ald.cloud.zuul.domain.FilterConfigDo;

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
  * @Date 18:05 2017/11/29
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有``限公司内部传阅，禁止外泄以及用于其他的商业目的
  */
 
public interface ConfigService extends BaseFilterService<FilterConfigDo> {

    List<FilterConfigDo> get(String key);

    void addListCache(String url, FilterConfigDo config);

}
