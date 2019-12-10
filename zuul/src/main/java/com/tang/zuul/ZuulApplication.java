package com.tang.zuul;

import com.tang.zuul.filter.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableOAuth2Sso
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public FirstPreFilter firstPreFilter(){
		return new FirstPreFilter();
	}

	@Bean
	public SecondPreFilter secondPreFilter(){
		return new SecondPreFilter();
	}

	@Bean
	public ThirdPreFilter thirdPreFilter(){
		return new ThirdPreFilter();
	}

	@Bean
	public ErrorFilter errorFilter(){
		return new ErrorFilter();
	}

	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}
}
