// package com.wh.mqtt.config;
//
// import org.eclipse.paho.client.mqttv3.*;
// import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
//
// /**
//  * <p> 创建一个 MQTT 客户端 </p>
//  *
//  * @author Wenhao Wang
//  * @version 1.0
//  * @date 2020-07-22
//  */
// public class MqttPushClient {
//
//     private static final Logger log = LoggerFactory.getLogger(MqttPushClient.class);
//     public static String MQTT_HOST = "";
//     public static String MQTT_CLIENT_ID = "";
//     public static String MQTT_USERNAME = "";
//     public static String MQTT_PASSWORD = "";
//     public static int MQTT_TIMEOUT = 10;
//     public static int MQTT_KEEPALIVE = 10;
//     private MqttClient client;
//     private static volatile MqttPushClient mqttPushClient = null;
//
//     /**
//      * 获取 MqttPushClient 对象 单例模式
//      *
//      * @return MqttPushClient 对象
//      */
//     public static MqttPushClient getInstance() {
//         if (mqttPushClient == null) {
//             synchronized (MqttPushClient.class) {
//                 if (mqttPushClient == null) {
//                     mqttPushClient = new MqttPushClient();
//                 }
//             }
//         }
//         return mqttPushClient;
//     }
//
//     private MqttPushClient() {
//         log.info("Connect MQTT: " + this);
//         connect();
//     }
//
//     private void connect() {
//         try {
//             client = new MqttClient(MQTT_HOST, MQTT_CLIENT_ID, new MemoryPersistence());
//             MqttConnectOptions option = new MqttConnectOptions();
//             option.setCleanSession(true);
//             option.setUserName(MQTT_USERNAME);
//             option.setPassword(MQTT_PASSWORD.toCharArray());
//             option.setConnectionTimeout(MQTT_TIMEOUT);
//             option.setKeepAliveInterval(MQTT_KEEPALIVE);
//             option.setAutomaticReconnect(true);
//
//             client.setCallback(new Callback());
//             client.connect(option);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
//
//     /**
//      * 发布主题，用于通知，默认 qos 为1，非持久化
//      *
//      * @param topic 主题
//      * @param data  数据
//      */
//     public void publish(String topic, String data) {
//         publish(topic, data, 1, false);
//     }
//
//     private void publish(String topic, String data, int qos, boolean retained) {
//         MqttMessage message = new MqttMessage();
//         message.setQos(qos);
//         message.setRetained(retained);
//         message.setPayload(data.getBytes());
//         MqttTopic mqttTopic = client.getTopic(topic);
//         if (mqttTopic == null) {
//             log.error("Topic Not Exist. [Topic:{}]", topic);
//             return;
//         }
//         try {
//             MqttDeliveryToken token = mqttTopic.publish(message);
//             token.waitForCompletion();
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }
//
//     /**
//      * 订阅某个主题 qos 默认为1
//      *
//      * @param topic 主题
//      */
//     public void subscribe(String topic) {
//         subscribe(topic, 1);
//     }
//
//     private void subscribe(String topic, int qos) {
//         try {
//             client.subscribe(topic,qos);
//         } catch (MqttException e) {
//             e.printStackTrace();
//         }
//     }
// }
