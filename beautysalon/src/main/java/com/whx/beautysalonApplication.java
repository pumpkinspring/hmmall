package com.whx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.whx.dao")
public class beautysalonApplication {
    public static void main(String[] args) {
        SpringApplication.run(beautysalonApplication.class,args);
    }
}
