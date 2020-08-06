package com.dhwooden.ep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableScheduling
public class EquipmentManagerApplication {
//	if(!registry.hasMappingForPattern("/static/**")){
//		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//	}
	public static void main(String[] args) {
		SpringApplication.run(EquipmentManagerApplication.class, args);
		System.out.println("SERVER START SUCCESS!");
	}
}
