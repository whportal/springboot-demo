package com.wh.mqtt.config;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> MQTT 回调 </p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-22
 */
public class Callback implements MqttCallback {

    private static final Logger log = LoggerFactory.getLogger(MqttCallback.class);

    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开连接..." + throwable);
    }

    /**
     * subscribe 订阅得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        log.info("Topic: {}", s);
        log.info("Message: {}", new String(mqttMessage.getPayload()));
        log.info("MqttMessage: {}", mqttMessage);
    }

    /**
     * publish 发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发布消息成功");
        log.info("deliveryComplete: {}", iMqttDeliveryToken);
    }
}
