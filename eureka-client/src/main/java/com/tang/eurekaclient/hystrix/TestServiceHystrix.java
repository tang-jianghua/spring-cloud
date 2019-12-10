package com.tang.eurekaclient.hystrix;

import com.tang.eurekaclient.service.TestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TestServiceHystrix implements TestService{
        @Override
        public String hello(@RequestParam(value = "name",required = false) String name) {
            return "hello" +name+", this messge send failed ";
    }
}
