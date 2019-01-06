package com.ald.cloud.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: aplus-cloud
 * @Version 1.0.0
 * @description:
 * @author: Mr.cai
 * @create: 2019-01-05 18:50
 * @CopyRight 本内容仅限于北境内部传阅，禁止外泄以及用于其他的商业目的
 **/
@RestController
@RequestMapping("/v1")
public class Controller {


    @Value("${content}")
    private String result;

    @ResponseBody
    @GetMapping(value = "/test")
    public String getTransferSend() {
        return result;
    }
}



