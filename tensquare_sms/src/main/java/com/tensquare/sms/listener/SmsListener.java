package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by XSH on 2018/12/5.
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @RabbitHandler
    public void executeSms(Map<String, String> map) {
        System.out.println("手机号:" + map.get("mobile"));
        System.out.println("验证码:" + map.get("checkcode"));
        /**
         * 监听消息发送验证码
         */
    }
}
