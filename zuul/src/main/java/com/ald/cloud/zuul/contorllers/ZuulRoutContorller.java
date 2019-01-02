//package com.ald.cloud.zuul.contorllers;
//
//import com.ald.cloud.zuul.services.ZuulRoutService;
//import com.ald.cloud.zuul.domain.ZuulRouteDo;
//
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
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
//  * @Date 15:55 2017/12/5
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//@RestController
//@RequestMapping("/")
//public class ZuulRoutContorller {
//
//    @Resource
//    ZuulRoutService service;
//
//    @RequestMapping("/routs/q")
//    public Map<String, Object> get(){
//        List<ZuulRouteDo> zuulRouteDos = service.listRouts();
//        Map<String, Object> resMap = new HashMap<>();
//
//        if(CollectionUtils.isEmpty(zuulRouteDos)){
//            resMap.put("code", 201);S
//            resMap.put("msg", "fail");
//        }else{
//            resMap.put("code", 200);
//            resMap.put("msg", "success");
//            resMap.put("list", zuulRouteDos);
//        }
//        return resMap;
//    }
//
//
//    @RequestMapping("/routs/p")
//    public Map<String, Object> set(){
//
//        ZuulRouteDo apiCs = new ZuulRouteDo();
//        apiCs.setId("api-a");
//        apiCs.setEnabled(Boolean.TRUE);
//        apiCs.setPath("/api-cs/**");
//        apiCs.setRetryable(Boolean.TRUE);
//        apiCs.setUrl("http://127.0.0.1/");
//        service.addRout(apiCs);
//
//        ZuulRouteDo apiFk = new ZuulRouteDo();
//        apiFk.setId("api-b");
//        apiFk.setEnabled(Boolean.TRUE);
//        apiFk.setPath("/api-fk/**");
//        apiFk.setRetryable(Boolean.TRUE);
//        apiFk.setServiceId("service-fk");
//        service.addRout(apiFk);
//
//        ZuulRouteDo rootFk = new ZuulRouteDo();
//        rootFk.setId("config-server");
//        rootFk.setEnabled(Boolean.TRUE);
//        rootFk.setPath("/config-server/**");
//        rootFk.setRetryable(Boolean.TRUE);
//        rootFk.setServiceId("config-server");
//        service.addRout(rootFk);
//
//
//        Map<String, Object> resMap = new HashMap<>();
//        resMap.put("code", 200);
//        resMap.put("msg", "success");
//        return resMap;
//    }
//}
