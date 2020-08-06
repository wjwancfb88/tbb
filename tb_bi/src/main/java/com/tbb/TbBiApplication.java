package com.tbb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TbBiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TbBiApplication.class, args);
        System.out.println("SERVER START SUCCESS!!");
    }
}