package com.xors.home.center.service;

import com.xors.home.center.mqtt.client.HomeMqttClient;
import com.xors.home.center.mqtt.server.HomeMqttServer;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class MqttService {

    @Resource
    private HomeMqttServer homeMqttServer;
    @Resource
    private HomeMqttClient homeMqttClient;

    @PostConstruct
    public void init() throws MqttException {
        homeMqttClient.init();
    }
}
