package com.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.personal.entity")  // 엔티티 모듈 경로
@SpringBootApplication
public class OwnerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OwnerApplication.class, args);
    }

}
