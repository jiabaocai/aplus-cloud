package com.ald.cloud.zuul.provider;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author wxy
 * zuul中使用hystrix的回退
 */
@Component
public class MyFallbackProvider implements ZuulFallbackProvider {

    @Override

    public String getRoute() {
        //路由配置micoserice-user，如果全部用"*"代替
        return "*";

    }

    @Override

    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse() {

            @Override
            public HttpStatus getStatusCode() throws IOException {
                //回退的状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //数字类型状态码
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                //状态文本
                return "ok";

            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                //回退响应体
                return new ByteArrayInputStream("服务不可用稍后再试".getBytes());

            }

            @Override
            public HttpHeaders getHeaders() {
                //header设置
                HttpHeaders headers = new HttpHeaders();
                MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mediaType);
                return headers;

            }

        };

    }

}


 
