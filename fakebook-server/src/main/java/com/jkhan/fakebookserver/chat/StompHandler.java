package com.jkhan.fakebookserver.chat;

import com.jkhan.fakebookserver.auth.JwtAuthenticationToken;
import com.jkhan.fakebookserver.auth.JwtProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

    @Autowired
    private JwtProvider jwtProvider;
    private int counter = 0;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("socket connected" + (++counter));
        StompHeaderAccessor headAccessor = StompHeaderAccessor.wrap(message);
        JwtAuthenticationToken unauthenticatedToken = new JwtAuthenticationToken(
                headAccessor.getFirstNativeHeader("Authorization")
        );
        Authentication authenticatedToken = jwtProvider.authenticate(unauthenticatedToken);
        return message;
    }

}
