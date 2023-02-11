package com.xors.home.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.xors.home.center")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xors.home.center")
@EnableEurekaClient
@EnableSwagger2
@EnableScheduling
public class CenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CenterApplication.class, args);
	}

}
