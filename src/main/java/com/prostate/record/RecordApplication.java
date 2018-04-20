package com.prostate.record;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix  //@EnableCircuitBreaker  ==  @EnableHystrix
@EnableFeignClients
@EnableEurekaServer
@SpringBootApplication
//@SpringCloudApplication
public class RecordApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordApplication.class, args);
	}
}
