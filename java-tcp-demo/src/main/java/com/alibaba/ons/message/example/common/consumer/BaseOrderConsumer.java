package com.alibaba.ons.message.example.common.consumer;

import com.alibaba.ons.message.example.common.MQConnectConfig;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by aaron on 2017/3/8.
 */
public class BaseOrderConsumer extends OrderConsumerBean {
    private String expression;
    private String topic;
    private String consumerId;
    private MQConnectConfig config;
    private MessageOrderListener messageListener;
    private String maxReconsumeTimes = "20";

    public BaseOrderConsumer() {
    }

    public void init() throws Exception {
        if(config == null || StringUtils.isEmpty(config.getAccessKey()) || StringUtils.isEmpty(config.getSecretKey())
                || StringUtils.isEmpty(config.getOnsAddr()) || StringUtils.isEmpty(consumerId)
                || StringUtils.isEmpty(expression) || StringUtils.isEmpty(topic) || null == messageListener)
            throw new Exception(" init order consumer failed, mandatory parameter is empty");

        Properties consumerProperties = new Properties();
        consumerProperties.setProperty(PropertyKeyConst.AccessKey, config.getAccessKey());
        consumerProperties.setProperty(PropertyKeyConst.SecretKey, config.getSecretKey());
        consumerProperties.setProperty(PropertyKeyConst.ONSAddr, config.getOnsAddr());

        consumerProperties.setProperty(PropertyKeyConst.MaxReconsumeTimes, maxReconsumeTimes);
        consumerProperties.setProperty(PropertyKeyConst.ConsumerId, consumerId);

        Subscription subscription = new Subscription();
        subscription.setExpression(expression);
        subscription.setTopic(topic);

        Map<Subscription, MessageOrderListener> subscriptionTable = new HashMap<>();
        subscriptionTable.put(subscription, messageListener);

        super.setProperties(consumerProperties);
        super.setSubscriptionTable(subscriptionTable);
        super.start();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public MQConnectConfig getConfig() {
        return config;
    }

    public void setConfig(MQConnectConfig config) {
        this.config = config;
    }

    public MessageOrderListener getMessageListener() {
        return messageListener;
    }

    public void setMessageListener(MessageOrderListener messageListener) {
        this.messageListener = messageListener;
    }

    public String getMaxReconsumeTimes() {
        return maxReconsumeTimes;
    }

    public void setMaxReconsumeTimes(String maxReconsumeTimes) {
        this.maxReconsumeTimes = maxReconsumeTimes;
    }
}
