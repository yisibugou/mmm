package com.mmm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MmmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MmmApplication.class, args);
    }
}
