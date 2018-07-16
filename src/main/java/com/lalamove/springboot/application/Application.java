package com.lalamove.springboot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.lalamove.springboot"})
@EnableJpaAuditing
@EnableJpaRepositories("com.lalamove.springboot.repository")
@EntityScan("com.lalamove.springboot.model")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
