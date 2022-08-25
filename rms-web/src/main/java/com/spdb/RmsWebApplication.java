package com.spdb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spdb.mapper")
public class RmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmsWebApplication.class, args);
    }

}
