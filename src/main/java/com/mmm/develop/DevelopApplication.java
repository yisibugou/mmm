package com.mmm.develop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.mmm.develop.user.dao")
@ServletComponentScan("com.mmm.develop.common.filter")
public class DevelopApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevelopApplication.class, args);
    }
}
