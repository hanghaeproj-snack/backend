package com.sparta.snackback.channel.service;

import com.sparta.snackback.channel.dto.ChannelRoomDto;
import com.sparta.snackback.channel.entity.Channel;
import com.sparta.snackback.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public ResponseEntity<List<ChannelRoomDto.Response>> showChannels() {
        List<Channel> channels = channelRepository.findAll();

        if (channels.size() == 0) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        List<ChannelRoomDto.Response> channelRoomDtoList = new ArrayList<>(channels.size());
        for (Channel channel : channels) {
            channelRoomDtoList.add(new ChannelRoomDto.Response(channel));
        }

        return ResponseEntity.ok(channelRoomDtoList);
    }
}
