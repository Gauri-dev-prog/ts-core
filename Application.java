package com.example.MyProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = "com.example.MyProject.dao")
@EnableAutoConfiguration
@ComponentScan("com.magic80.springbootcommon")
@ComponentScan("com.example.MyProject")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
