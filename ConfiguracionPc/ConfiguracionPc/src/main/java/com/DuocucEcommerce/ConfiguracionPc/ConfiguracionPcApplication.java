package com.DuocucEcommerce.ConfiguracionPc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ConfiguracionPcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiguracionPcApplication.class, args);
	}

}
