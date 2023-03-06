package com.sparta.snackback.channel.controller;

import com.sparta.snackback.channel.dto.ChannelRoomDto;
import com.sparta.snackback.channel.service.ChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/channel")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    // 채널 생성
    @PostMapping
    public ResponseEntity<ChannelRoomDto.Response> postChannel(@RequestBody ChannelRoomDto.Request request) {
        return channelService.createChannel(request);
    }


}
