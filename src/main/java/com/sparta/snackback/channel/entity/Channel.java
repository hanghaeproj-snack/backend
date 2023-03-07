package com.sparta.snackback.channel.entity;

import com.sparta.snackback.channel.dto.ChannelRoomDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private Boolean isShow;

    public Channel(ChannelRoomDto.Request request) {
        this.uuid = UUID.randomUUID().toString();
        this.title = request.getTitle();
        this.isShow = true;
    }

    public void delete(Boolean isShow) {
        this.isShow = isShow;
    }

}
