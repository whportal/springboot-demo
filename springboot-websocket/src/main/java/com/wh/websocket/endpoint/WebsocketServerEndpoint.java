package com.wh.websocket.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/13
 */
@Component
@ServerEndpoint("/")
public class WebsocketServerEndpoint {

    private final Logger log = LoggerFactory.getLogger(WebsocketServerEndpoint.class);

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        log.info("onOpen:[session:{} 接入]", session);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("onMessage:[session:{} 接收到一条消息：{}]", session, message);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        log.info("onClose:[session:{} 连接关闭，关闭原因为：{}]", session, closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        log.info("onError:[session:{} 发生异常", session, throwable);
    }
}
