package com.DuocucEcommerce.Compatibilidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class CompatibilidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompatibilidadApplication.class, args);
	}

}
