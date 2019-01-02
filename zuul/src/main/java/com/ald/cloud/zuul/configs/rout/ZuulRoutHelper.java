//package com.ald.cloud.zuul.configs.rout;
//
//import com.ald.cloud.zuul.domain.ZuulRouteDo;
//import com.ald.cloud.zuul.services.ZuulRoutService;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
//import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
//import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
//import org.springframework.util.StringUtils;
//
//import java.util.LinkedHashMap;
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
//  * @Date 15:29 2017/12/5
//  * @Desription
//  * @CopyRight 本内容仅限于杭州阿拉丁信息科技股份有限公司内部传阅，禁止外泄以及用于其他的商业目的
//  */
//
//public class ZuulRoutHelper extends SimpleRouteLocator implements RefreshableRouteLocator {
//
//    public final static Logger logger = LoggerFactory.getLogger(ZuulRoutHelper.class);
//
//    private ZuulProperties properties;
//
//    private ZuulRoutService zuulRoutService;
//
//    public ZuulRoutHelper(String servletPath, ZuulProperties properties, ZuulRoutService zuulRoutService) {
//        super(servletPath, properties);
//        this.properties = properties;
//        this.zuulRoutService = zuulRoutService;
//        logger.info("servletPath:{}", servletPath);
//    }
//
//
//    @Override
//    public void refresh() {
//        doRefresh();
//    }
//
//    @Override
//    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
//        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
//        routesMap.putAll(super.locateRoutes());
//
//        //从redis/ehcache中加载路由信息
//        routesMap.putAll(locateRoutesFromDB());
//        //加/
//        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
//        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
//            String path = entry.getKey();
//
//            if (!path.startsWith("/")) {
//                path = "/" + path;
//            }
//            if (StringUtils.hasText(this.properties.getPrefix())) {
//                path = this.properties.getPrefix() + path;
//                if (!path.startsWith("/")) {
//                    path = "/" + path;
//                }
//            }
//            values.put(path, entry.getValue());
//        }
//        return values;
//    }
//
//    private Map<String, ZuulProperties.ZuulRoute> locateRoutesFromDB() {
//        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
//        //获取配置
//        List<ZuulRouteDo> results = zuulRoutService.listRouts();
//        for (ZuulRouteDo result : results) {
//            if (StringUtils.isEmpty(result.getPath()) ) {
//                continue;
//            }
//            if (StringUtils.isEmpty(result.getServiceId()) && StringUtils.isEmpty(result.getUrl())) {
//                continue;
//            }
//            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
//            try {
//                BeanUtils.copyProperties(result, zuulRoute);
//            } catch (Exception e) {
//                logger.error("an error occurred while getting routs", e);
//            }
//            routes.put(zuulRoute.getPath(), zuulRoute);
//        }
//        return routes;
//    }
//
//}
