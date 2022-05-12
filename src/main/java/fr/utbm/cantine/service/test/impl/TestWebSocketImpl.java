package fr.utbm.cantine.service.test.impl;

import fr.utbm.cantine.config.websocket.WebSocketServer;
import fr.utbm.cantine.service.test.ITestWebSocket;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@EnableScheduling
public class TestWebSocketImpl implements ITestWebSocket {

//    @Scheduled(fixedRate=2000) //1000毫秒执行一次
    @Override
    public void printTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String date = dateFormat.format(new Date());
        WebSocketServer.sendInfo(date,"1");
        System.out.println(date);
    }
}
