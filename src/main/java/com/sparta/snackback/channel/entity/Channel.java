package com.sparta.snackback.channel.entity;

import com.sparta.snackback.channel.dto.ChannelRoomDto;
import com.sparta.snackback.user.entity.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Channel(ChannelRoomDto.Request request, User user) {
        this.uuid = UUID.randomUUID().toString();
        this.title = request.getTitle();
        this.isShow = true;
        this.user = user;
    }

    public void delete(Boolean isShow) {
        this.isShow = isShow;
    }

}
