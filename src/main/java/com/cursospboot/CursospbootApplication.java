package com.cursospboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.cursospboot.model") /* Declara onde ficarão as entidade */
@ComponentScan(basePackages = "com.*") // caso ele n encontre controllers, forçar o scan de componentes
public class CursospbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursospbootApplication.class, args);
	}

}
