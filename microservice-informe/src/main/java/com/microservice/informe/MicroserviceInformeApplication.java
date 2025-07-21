package com.microservice.informe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceInformeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceInformeApplication.class, args);
	}

}
