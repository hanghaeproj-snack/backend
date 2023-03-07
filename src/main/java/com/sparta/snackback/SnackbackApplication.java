package com.sparta.snackback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SnackbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnackbackApplication.class, args);
    }

}
