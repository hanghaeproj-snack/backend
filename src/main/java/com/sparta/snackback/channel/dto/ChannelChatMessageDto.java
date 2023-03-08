package com.sparta.snackback.channel.dto;

import com.sparta.snackback.channel.entity.ChannelMessage;
import lombok.Getter;

public class ChannelChatMessageDto {

    public enum MessageType {
        ENTER, TALK
    }

    @Getter
    public static class Publisher {

        private MessageType type;
        private String roomId;
        private Long userId;
        private String sender;
        private String message;

        public void setMessage(String message) {
        this.message = message;
        }

    }

    @Getter
    public static class Subscriber {

        private Long id;
        private Long userId;
        private String nickname;
        private String message;
        private String createdAt;

        public Subscriber(ChannelMessage channelMessage) {
            this.id = channelMessage.getId();
            this.userId = channelMessage.getUser().getId();
            this.nickname = channelMessage.getUser().getNickname();
            this.message = channelMessage.getContents();
            this.createdAt = channelMessage.getCreatedAt().toString().replace("T", " T").substring(0, 20);
        }

    }

}
