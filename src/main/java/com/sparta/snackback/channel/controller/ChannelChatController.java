package com.sparta.snackback.channel.controller;

import com.sparta.snackback.channel.dto.ChannelChatMessageDto;
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
    public void enter(ChannelChatMessageDto.Send send) {
        log.info("### {}", send.getSender() + " 참여");
        send.setMessage(send.getSender() + " 참여");
        template.convertAndSend("/topic/channel/room/" + send.getRoomId(), send);
    }

    // 채널 채팅방 채팅 API
    @MessageMapping("/channel/chat/message")
    public void message(ChannelChatMessageDto.Send send) {
        log.info("### {}", send.getSender() + ": " + send.getMessage());
        template.convertAndSend("/topic/channel/room/" + send.getRoomId(), send);
    }

}
