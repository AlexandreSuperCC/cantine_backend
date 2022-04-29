package fr.utbm.cantine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Type WebSocketConfig.java
 * @Desc configuration of websocket
 * @author yuan.cao@utbm.fr
 * @date 28/04/2022 19:25
 * @version 1.0
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }
}

