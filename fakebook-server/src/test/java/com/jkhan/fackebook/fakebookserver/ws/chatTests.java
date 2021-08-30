package com.jkhan.fackebook.fakebookserver.ws;

import java.lang.reflect.Type;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.jkhan.fakebookserver.FakebookServerApplication;
import com.jkhan.fakebookserver.chat.ChatMessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@SpringBootTest(classes = FakebookServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class chatTests {


    @Test
    public void sendChatMessageTest() throws ExecutionException, InterruptedException, TimeoutException {

        WebSocketClient wsClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(wsClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add("Authorization", "Bearer " +
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MzAyMjE4MDUsImV4cCI6MTYzMDg4MTgwNSwibmFtZSI6Imppbmt5dWhhbiIsInVzZXJJZCI6ImZhNmZjNjQzLTYxYzYtNDIyMC05MzM1LTI4ZGUzNTI3N2VkNiIsImVtYWlsIjoiZ2tzd2xzcmI5NUBnbWFpbC5jb20ifQ.jIVo4WJiMUA4QAoxt8xXqAckppoubqyjn0sad_vgKFQ"
                );
        StompSession stompSession =  stompClient.connect("ws://localhost:8080/ws", headers, new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/sub/test", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return ChatMessage.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {

                    }
                });
            }
        }).get(10, TimeUnit.SECONDS);
        new Scanner(System.in).nextLine();
    }
}