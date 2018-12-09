package com.tensquare.test;

import com.tensquare.rabbitmq.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * Created by XSH on 2018/12/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式
     */
    @Test
    public void sendmsg1() {
        rabbitTemplate.convertAndSend("itcast", "直接消息队列");
    }

    /**
     * 分裂模式
     */
    @Test
    public void sendmsg2() {
        rabbitTemplate.convertAndSend("chuanzhi", "", "分裂模式消息队列");
    }

    /**
     * 主题模式
     */
    @Test
    public void sendmsg3() {
        rabbitTemplate.convertAndSend("topic84", "good.log", "主题模式消息队列");
    }

}
