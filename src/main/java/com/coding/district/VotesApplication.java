package com.coding.district;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotesApplication.class, args);
    }
}
