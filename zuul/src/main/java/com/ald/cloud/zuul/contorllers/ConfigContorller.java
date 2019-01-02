package com.ald.cloud.zuul.contorllers;

import com.ald.cloud.zuul.services.ConfigService;
import com.ald.cloud.zuul.Utils.JwtTokenUtil;
import com.ald.cloud.zuul.constants.Constants;
import com.ald.cloud.zuul.domain.FilterConfigDo;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

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
  * @Date 18:00 2017/11/29
  * @Desription 
  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
  */

@RestController
@RequestMapping("/")
public class ConfigContorller {

    @Resource
    ConfigService configService;

    //查询数据
    @RequestMapping("/q")
    public Map<String, Object> getAllConfig(@NotNull String url){
        List<FilterConfigDo> filterConfigDos = configService.get(url);
        Map<String, Object> resMap = new HashMap<>();

        if(CollectionUtils.isEmpty(filterConfigDos)){
            resMap.put("code", 201);
            resMap.put("msg", "fail");
        }else{
            resMap.put("code", 200);
            resMap.put("msg", "success");
            resMap.put("list", filterConfigDos);
        }
        return resMap;
    }

    @RequestMapping("/p")
    public Map<String, Object> push(@NotNull String url){

        FilterConfigDo ipBlackConfig = new FilterConfigDo();
        ipBlackConfig.setConfirmRule(Constants.FILTER_BLACK);
        List<String> ipBlackList = new ArrayList<>();
        ipBlackList.add("192.168.1.1");
        ipBlackConfig.setList(ipBlackList);
        configService.addListCache(url, ipBlackConfig);

        FilterConfigDo ipWhiteConfig = new FilterConfigDo();
        ipWhiteConfig.setConfirmRule(Constants.FILTER_WHITE);
        List<String> ipWhiteList = new ArrayList<>();
        ipWhiteList.add("192.168.117.22");
        ipWhiteList.add("127.0.0.1");
        ipWhiteList.add("192.168.117.168");
        ipWhiteConfig.setList(ipWhiteList);
        configService.addListCache(url, ipWhiteConfig);

        FilterConfigDo md5Config = new FilterConfigDo();
        md5Config.setConfirmRule(Constants.FILTER_MD5);
        md5Config.setKey("12345678");
        md5Config.setKeyColumn("sign");
        List<String> md5Params = new ArrayList<>();
        md5Params.add("url");
        md5Config.setList(md5Params);
        configService.addListCache(url, md5Config);

        FilterConfigDo rsaConfig = new FilterConfigDo();
        rsaConfig.setConfirmRule(Constants.FILTER_RSA);
        List<String> rsaParamList = new ArrayList<>();
        rsaParamList.add("url");
        rsaParamList.add("sign");
        rsaConfig.setList(rsaParamList);
        rsaConfig.setKeyColumn("signRsa");
        configService.addListCache(url, rsaConfig);

        FilterConfigDo tokenConfig = new FilterConfigDo();
        tokenConfig.setConfirmRule(Constants.FILTER_TOKEN);
        configService.addListCache(url, tokenConfig);

        Map<String, Object> resMap = new HashMap<>();
        resMap.put("code", 200);
        resMap.put("msg", "success");
        Map<String, Object> tokenMap = new HashMap<>();
        resMap.put("token", JwtTokenUtil.generateToken(tokenMap, Constants.SECRET_TOKEN));
        return resMap;
    }

}
