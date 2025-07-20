package com.microservice.fraccionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceFraccionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceFraccionamientoApplication.class, args);
	}

}
