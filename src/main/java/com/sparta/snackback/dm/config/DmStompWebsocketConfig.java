package com.sparta.snackback.dm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class DmStompWebsocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/stomp/chat")
//                .setAllowedOrigins("*")
//                .withSockJS(); // 얘 때문에 포스트맨 안됨 >> 이거 키면 포스트맨 http 붙음 ㅠㅠ
        registry.addEndpoint("/ws/chat").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        //client 에서 send 요청 처리
        registry.setApplicationDestinationPrefixes("/app");

        registry.enableSimpleBroker("/queue","/topic");
    }
}
