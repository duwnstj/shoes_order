package com.personal.common.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {
    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("src/main/resources")  // .env 파일의 경로
                .load();
    }
}