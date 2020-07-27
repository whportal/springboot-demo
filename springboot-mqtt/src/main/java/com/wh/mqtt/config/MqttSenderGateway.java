package com.wh.mqtt.config;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-22
 */
@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttSenderGateway {

    /**
     * 发送消息到 MQTT 服务器
     *
     * @param data 发送的消息
     */
    void sendToMqtt(String data);

    /**
     * 发送消息到 MQTT 服务器
     *
     * @param topic   主题
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * 发送消息到 MQTT 服务器
     *
     * @param topic   主题
     * @param qos     消息质量
     *                0：表示订阅者没收到消息不会再次发送，消息会丢失；
     *                1：表示会尝试重试，一直到接收到消息，可能导致订阅者收到多次重复消息
     *                2：多了一次去重操作，确保订阅者收到的消息只有一次
     * @param payload 消息主体
     */
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
