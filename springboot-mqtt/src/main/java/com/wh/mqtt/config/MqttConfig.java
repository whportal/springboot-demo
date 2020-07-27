package com.wh.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.util.UUID;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-22
 */
@Configuration
@IntegrationComponentScan(basePackages = "com.wh.mqtt")
public class MqttConfig {

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.sender.clientId}")
    private String senderClientId;

    @Value("${mqtt.sender.defaultTopic}")
    private String senderDefaultTopic;

    @Value("${mqtt.receiver.clientId}")
    private String receiverClientId;

    @Value("${mqtt.receiver.defaultTopic}")
    private String receiverDefaultTopic;

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();

        // 设置连接的地址
        options.setServerURIs(host.split(","));
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置连接的超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会向客户端发送心跳判断客户端是否在线
        options.setKeepAliveInterval(20);
        // 设置自动重连
        options.setAutomaticReconnect(true);
        // 设置 遗嘱 消息的 Topic 若客户端与服务器之前的连接意外中断，服务器将发布客户端的 遗嘱 消息
        options.setWill("willTopic", "offline...".getBytes(), 2, false);

        factory.setConnectionOptions(options);
        return factory;
    }

    /**
     * 生产者
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(senderClientId, mqttPahoClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(senderDefaultTopic);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(host, UUID.randomUUID().toString(), receiverDefaultTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> System.out.println("接收到消息：" + message.getPayload());
    }
}
