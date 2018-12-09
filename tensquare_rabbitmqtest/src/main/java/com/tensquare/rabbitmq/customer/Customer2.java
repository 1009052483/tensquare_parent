package com.tensquare.rabbitmq.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by XSH on 2018/12/5.
 */
@Component
@RabbitListener(queues = "ithema")
public class Customer2 {

    @RabbitHandler
    public void getMsg(String msg) {
        System.out.println("ithema:" + msg);
    }
}
