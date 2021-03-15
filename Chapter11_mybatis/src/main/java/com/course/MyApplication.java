package com.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.PreDestroy;

@SpringBootApplication
@MapperScan("com.course.mapper")
public class MyApplication {
    private static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
    }
    @PreDestroy
    public void close(){
        MyApplication.context.close();
    }
}
