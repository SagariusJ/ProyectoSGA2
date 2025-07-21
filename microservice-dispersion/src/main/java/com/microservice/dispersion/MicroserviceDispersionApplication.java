package com.microservice.dispersion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceDispersionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDispersionApplication.class, args);
	}

}
