package com.ald.cloud.zuul.domain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.Serializable;
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
  * @Date 16:24 2017/11/29
  * @Desription  接口验证规则, 存储于redis和ehcache中, list
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */

@Component
public class FilterConfigDo implements Serializable{

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    /**
     * 列表参数
     */
    private List<String> list;

    /**
     * 接口验证方式
     */
    private Integer confirmRule;


    /**
     * 密钥/盐值/私钥...具体含义根据 接口验证方式 定义
     */
    private String key;

    /**
     * key表示的字段
     */
    private String keyColumn;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Integer getConfirmRule() {
        return confirmRule;
    }

    public void setConfirmRule(Integer confirmRule) {
        this.confirmRule = confirmRule;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(String keyColumn) {
        this.keyColumn = keyColumn;
    }
}
