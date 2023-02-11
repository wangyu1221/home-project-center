package com.xors.home.center.mqtt.server;

import io.moquette.broker.Server;
import io.moquette.broker.config.IConfig;
import io.moquette.broker.config.MemoryConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Component
public class HomeMqttServer {

    private Server server;

    @PostConstruct
    public void init(){
        final Properties properties = new Properties();
        properties.setProperty("port", String.valueOf(1883));
        properties.setProperty("host", "0.0.0.0");
        properties.setProperty("allow_anonymous", "true");
        IConfig config = new MemoryConfig(properties);
        server = new Server();
        try {
            server.startServer(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
