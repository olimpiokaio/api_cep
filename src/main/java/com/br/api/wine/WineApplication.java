package com.br.api.wine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class WineApplication {

	public static void main(String[] args) {
		SpringApplication.run(WineApplication.class, args);
	}

}
