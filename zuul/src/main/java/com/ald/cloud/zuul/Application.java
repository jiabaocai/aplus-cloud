package com.ald.cloud.zuul;

import com.ald.cloud.zuul.filter.AccessFilter;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringBootApplication
@EnableDiscoveryClient
@EnableApolloConfig
public class Application {


    public static void main(String[] args) {
        //启动应用
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }


}


