package com.xors.home.center;

import com.xors.home.center.mqtt.client.HomeMqttClient;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private HomeMqttClient homeMqttClient;

    @ApiOperation("")
    @PostMapping("/test/send")
    public void send(@RequestParam String payload) throws MqttException {
        homeMqttClient.publish(payload);
    }
}
