package com.fangxing.javalearning.annotationlearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.fangxing.javalearning.annotationlearning")
@EnableAutoConfiguration
public class ComponentScanApp {


    public static void main(String[] args) {

        System.out.println("hello world");
        SpringApplication.run(ComponentScanApp.class, args);
    }

}
