package com.tang.eurekaclient.service;

import com.tang.eurekaclient.hystrix.TestServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "eureka-service-provider",fallback = TestServiceHystrix.class)
public interface TestService {

    @GetMapping("/hello")
     String hello(@RequestParam(value = "name",required = false) String name);
}
