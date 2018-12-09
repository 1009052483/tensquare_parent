package com.tensquare.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * Created by XSH on 2018/12/5.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

//    @Bean
//    public IdWorker idWorker() {
//        return new IdWorker(1, 1);
//    }
}
