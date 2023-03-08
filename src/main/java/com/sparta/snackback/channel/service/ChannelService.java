package com.sparta.snackback.channel.service;

import com.sparta.snackback.channel.dto.ChannelChatMessageDto;
import com.sparta.snackback.channel.dto.ChannelRoomDto;
import com.sparta.snackback.channel.entity.Channel;
import com.sparta.snackback.channel.entity.ChannelJoiner;
import com.sparta.snackback.channel.entity.ChannelMessage;
import com.sparta.snackback.channel.repository.ChannelJoinerRepository;
import com.sparta.snackback.channel.repository.ChannelMessageRepository;
import com.sparta.snackback.channel.repository.ChannelRepository;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;
    private final ChannelJoinerRepository channelJoinerRepository;
    private final ChannelMessageRepository channelMessageRepository;

    private final UserRepository userRepository;

    // 채널 생성
    @Transactional
    public ResponseEntity<ChannelRoomDto.Response> createChannel(ChannelRoomDto.Request request, User user) {
        Optional<Channel> optionalChannel = channelRepository.findByTitle(request.getTitle());
        if (optionalChannel.isPresent()) {
            throw new IllegalArgumentException("중복된 title");
        }

        Channel channel = channelRepository.save(new Channel(request, user));

        // 채널 생성자 추가
        channelJoinerRepository.save(new ChannelJoiner(channel, user));

        return ResponseEntity.ok(new ChannelRoomDto.Response(channel));
    }

    // 채널 전체 조회
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

    // 채널 초대
    @Transactional
    public void channelInvite(Long channelId, List<Long> userIdList) {
        Channel channel = channelRepository.findById(channelId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채널"));

        for (Long userId : userIdList) {
            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

            channelJoinerRepository.save(new ChannelJoiner(channel, user));
        }
    }


    /*
        Channel Message
     */
    @Transactional
    public ChannelChatMessageDto.Subscriber saveChannelMessage(ChannelChatMessageDto.Publisher publisher) {
        Channel channel = channelRepository.findByUuid(publisher.getRoomId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채널"));
        
        // 유저 존재 여부
        User user = userRepository.findById(publisher.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        ChannelMessage channelMessage = channelMessageRepository.save(new ChannelMessage(channel, user, publisher));
        return new ChannelChatMessageDto.Subscriber(channelMessage);
    }
}
