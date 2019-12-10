package com.tang.eurekaserviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {

    @Value("${provider}")
    String provider;

    @GetMapping("/hello")
    public String index(@RequestParam(name = "name",required = false,defaultValue = "tang") String name) {
        return "hello " + name + "，this is first messge";
    }

    @GetMapping("/getProvider")
    public String getProvider(){
        return "provider: "+provider;
    }
}