package com.jkhan.fakebookserver.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // topic 구독 위한 경로 - 메세지 구독
        registry.enableSimpleBroker("/topic");
        // @MessageMapping 의 프리픽스 경로 설정 - 메세지 발행
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // STOMP  최초 연결을 위한 엔드포인트 설정
        registry.addEndpoint("/ws").withSockJS();
   }
}
