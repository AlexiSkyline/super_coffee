package com.super_coffee.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @Slf4j
public class SuperCoffeeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuperCoffeeApplication.class, args);
	}
}
