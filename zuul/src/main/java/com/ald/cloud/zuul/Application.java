package com.ald.cloud.zuul;

import com.ald.cloud.zuul.Utils.FileUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@EnableZuulProxy
//@EnableEurekaClient
@SpringBootApplication
@EnableCaching
@RestController
public class Application {

    public static void main(String[] args) {
        //检查filterOrder顺序是否没有重复
//      filterOrderDuplicated();
        //启动应用
        SpringApplication.run(Application.class, args);
    }

    /**
     * 启动前检查filter order是否重复
     */
    private static void filterOrderDuplicated() {
        String filterRelativePath = "/src/main/java/com/ald/cloud/zuul/filters";
        String path = System.getProperty("user.dir").replaceAll("\\\\", "/") + filterRelativePath;
        File filterDir = new File(path);
        List<Integer> orders = new ArrayList<Integer>();
        if (filterDir.exists() && filterDir.isDirectory()) {
            for (File filter : filterDir.listFiles()) {
                String filterOrder = FileUtil.printMethodFromFile(filter);
                Integer order = Integer.parseInt(
                        filterOrder.replaceAll("@FilterOrder", "")
                                .replaceAll("\\(", "")
                                .replaceAll("\\)", "")
                                .replaceAll(" ", "")
                );
                if (orders.contains(order)) {
                    Assert.isTrue(false, "duplicated filter order:" + order);
                } else {
                    orders.add(order);
                }
            }
        }
    }
}


