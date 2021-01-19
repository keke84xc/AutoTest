package com.keke84.code.get;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//组件扫描
@ComponentScan("com.keke84.code")
public class Application {
    public static void main(String[] args) {
        //固定写法
        SpringApplication.run(Application.class,args);
    }
}
