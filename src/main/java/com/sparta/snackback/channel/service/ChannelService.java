package com.sparta.snackback.channel.service;

import com.sparta.snackback.channel.dto.ChannelRoomDto;
import com.sparta.snackback.channel.entity.Channel;
import com.sparta.snackback.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    // 채널 생성
    @Transactional
    public ResponseEntity<ChannelRoomDto.Response> createChannel(ChannelRoomDto.Request request) {
        Channel channel = channelRepository.save(new Channel(request));
        return ResponseEntity.ok(new ChannelRoomDto.Response(channel));
    }

}
