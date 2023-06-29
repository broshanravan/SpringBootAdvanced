package com.mehr.SpringBootAdvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class SpringBootAdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAdvancedApplication.class, args);
		System.out.println("Kosde Faaheshe-ye Zahra Kir-e sag");
	}

}
