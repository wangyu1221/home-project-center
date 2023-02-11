package com.xors.home.center.mqtt.client;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

@Component
public class HomeMqttClient {

    private final String topic = "test";
    private final int qos = 2;
    private final String broker = "tcp://127.0.0.1:1883";
    private final String clientId = "control-center";
    private final MemoryPersistence persistence = new MemoryPersistence();

    private MqttClient mqttClient;

    public void init() throws MqttException {
        mqttClient = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: "+broker);
        mqttClient.connect(connOpts);
    }

    public void publish(String content) throws MqttException {
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(qos);
        mqttClient.publish(topic, message);
    }
}
