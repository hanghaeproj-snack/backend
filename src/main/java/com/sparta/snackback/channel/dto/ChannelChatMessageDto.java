package com.sparta.snackback.channel.dto;

import lombok.Getter;

public class ChannelChatMessageDto {

    public enum MessageType {
        ENTER, TALK
    }

    @Getter
    public static class Send {

        private MessageType type;
        private String roomId;
        private String sender;
        private String message;

        public void setMessage(String message) {
        this.message = message;
        }

    }

//    @Getter
//    public static class

}
