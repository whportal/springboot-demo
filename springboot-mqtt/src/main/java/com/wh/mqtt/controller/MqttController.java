package com.wh.mqtt.controller;

import com.wh.mqtt.config.MqttSenderGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p></p>
 *
 * @author Wenhao Wang
 * @version 1.0
 * @date 2020-07-22
 */
@RestController
public class MqttController {

    @Autowired
    private MqttSenderGateway mqttSenderGateway;

    @GetMapping("send/{data}")
    public void sendWithDefaultTopic(@PathVariable("data") String data) {
        mqttSenderGateway.sendToMqtt(data);
    }

    @GetMapping("send/{topic}/{data}")
    public void sendWithTopic(@PathVariable("topic") String topic, @PathVariable("data") String data) {
        mqttSenderGateway.sendToMqtt(topic, 1, data);
    }
}
