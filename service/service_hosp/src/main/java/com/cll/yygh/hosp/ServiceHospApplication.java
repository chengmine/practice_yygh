package com.cll.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author chengll
 * @Date 2023/4/9 21:46
 * @Desc ServiceHospApplication
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.cll")
@EnableOpenApi
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
