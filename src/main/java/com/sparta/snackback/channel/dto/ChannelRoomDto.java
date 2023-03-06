package com.sparta.snackback.channel.dto;

import com.sparta.snackback.channel.entity.Channel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChannelRoomDto {

    // 둘 다 나중에 userList 추가하기

    @Getter
    @NoArgsConstructor
    public static class Request {

        private String title;

        public Request(String title) {
            this.title = title;
        }

    }

    @Getter
    public static class Response {

        private final String uuid;
        private final String title;

        public Response(Channel channel) {
            this.uuid = channel.getUuid();
            this.title = channel.getTitle();
        }

    }

}
