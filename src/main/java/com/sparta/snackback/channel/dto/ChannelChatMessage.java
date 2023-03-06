package com.sparta.snackback.channel.dto;

import lombok.Getter;

@Getter
public class ChannelChatMessage {

    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String date;

    public void setDate(String date) {
        this.date = date;
    }
}
