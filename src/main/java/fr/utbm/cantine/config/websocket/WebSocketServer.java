package fr.utbm.cantine.config.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Type WebSocketServer.java
 * @Desc WebSocket handler class
 * @Doc https://blog.csdn.net/qq_31960623/article/details/114131424
 * @author yuan.cao@utbm.fr
 * @date 28/04/2022 20:44
 * @version 1.0
 */
@Component
@Slf4j
@ServerEndpoint("/ws/connection/{cantineID}")
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收cantineID*/
    private String cantineID = "";

    /*********************************core configuration code*********************************/

    /**
     * the method to call if the connection is done
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("cantineID") String cantineID) {
        this.session = session;
        this.cantineID=cantineID;
        if(webSocketMap.containsKey(cantineID)){
            webSocketMap.remove(cantineID);
            //加入set中
            webSocketMap.put(cantineID,this);
        }else{
            //加入set中
            webSocketMap.put(cantineID,this);
            //在线数加1
            addOnlineCount();
        }
        log.info("The connection of the client : "+cantineID+", current online clients : " + getOnlineCount());
        sendMessage("connection succeeds");
    }

    /**
     * the method to call when the connection is over
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(cantineID)){
            webSocketMap.remove(cantineID);
            //从set中删除
            subOnlineCount();
        }
        log.info("Client exits : "+cantineID+", current online clients : " + getOnlineCount());
    }

    /**
     * The method to call after receiving message from clients
     * @param message
     * message coming from the client
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("Client : "+cantineID+" 's message: : "+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("from cantineID: ",this.cantineID);
                String toCanteenID=jsonObject.getString("toCanteenID");
                //传送给对应toCanteenID用户的websocket
                if(StringUtils.isNotBlank(toCanteenID)&&webSocketMap.containsKey(toCanteenID)){
                    webSocketMap.get(toCanteenID).sendMessage(message);
                }else{
                    //否则不在这个服务器上，发送到mysql或者redis
                    log.error("The requested canteenID: "+toCanteenID+" is not on the server");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        log.error("Client's error : "+this.cantineID+", cause :"+error.getMessage());
        error.printStackTrace();
    }

    /*********************************core configuration code*********************************/

    /*********************************core sender code*********************************/

    /**
     * server pushes message
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * send custom message
     **/
    public static void sendInfo(String message, String cantineID) {
        log.info("Send message to : "+cantineID+"，text : "+message);
        if(StringUtils.isNotBlank(cantineID) && webSocketMap.containsKey(cantineID)){
            webSocketMap.get(cantineID).sendMessage(message);
        }else{
            log.error("Clients "+cantineID+", offline ！");
        }
    }

    /*********************************core sender code*********************************/

    /**
     * get online number
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * online number +1
     */
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    /**
     * online number -1
     */
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}

