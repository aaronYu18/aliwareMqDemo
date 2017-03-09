package com.alibaba.ons.message.example.consumer;

import com.alibaba.ons.message.example.common.consumer.OrderConsumerBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * MQ 通过Spring方式接收消息示例 Demo
 */
public class MQConsumer4Spring {
    /**
     * 启动测试之前请修改配置文件:resources/consumer/consumer.xml
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("consumer/consumer.xml");
//        ConsumerBean consumer = (ConsumerBean) ctx.getBean("consumer");
      /*  Consumer consumer = (ConsumerBean) ctx.getBean("consumerOne");
        System.out.println("BaseConsumer Started");*/
        OrderConsumerBean consumer = (OrderConsumerBean) ctx.getBean("consumerOrderOne");
        System.out.println("BaseConsumer Started");
    }

}
