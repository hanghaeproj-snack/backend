package com.sparta.snackback.channel.controller;

import com.sparta.snackback.channel.dto.ChannelChatMessageDto;
import com.sparta.snackback.channel.service.ChannelService;
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

    private final ChannelService channelService;

    // 채널 채팅방 입장 API
    @MessageMapping("/channel/publisher/enter")
    public void enter(ChannelChatMessageDto.Publisher publisher) {
        log.info("### {}", publisher.getSender() + " 참여");
        publisher.setMessage(publisher.getSender() + " 참여");

        template.convertAndSend("/topic/channel/room/" + publisher.getRoomId(), channelService.saveChannelMessage(publisher));
    }

    // 채널 채팅방 채팅 API
    @MessageMapping("/channel/publisher/message")
    public void message(ChannelChatMessageDto.Publisher publisher) {
        log.info("### {}", publisher.getSender() + ": " + publisher.getMessage());

        template.convertAndSend("/topic/channel/room/" + publisher.getRoomId(), channelService.saveChannelMessage(publisher));
    }

}
