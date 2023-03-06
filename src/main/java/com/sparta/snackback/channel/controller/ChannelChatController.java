package com.sparta.snackback.channel.controller;

import com.sparta.snackback.channel.dto.ChannelChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ChannelChatController {

    /*
        STOMP Message Handle Method
     */
    // @ToSend 프론트랑 연결 후 사용해보기
    private final SimpMessagingTemplate template;

    // 채널 채팅방 입장 API
    @MessageMapping("/channel/chat/enter")
    public void enter(ChannelChatMessage message) {
        log.info("### {}", message.getSender() + " 참여");
        message.setDate(message.getSender() + " 참여");
        template.convertAndSend("/sub/topic/channel/room/" + message.getRoomId(), message);
    }

    // 채널 채팅방 채팅 API
    @MessageMapping("/channel/chat/message")
    public void message(ChannelChatMessage message) {
        log.info("### {}", message.getSender() + ": " + message.getDate());
        template.convertAndSend("/sub/topic/channel/room/" + message.getRoomId(), message);
    }

}
