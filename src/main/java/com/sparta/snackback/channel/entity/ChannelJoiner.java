package com.sparta.snackback.channel.entity;

import com.sparta.snackback.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ChannelJoiner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Boolean isShow;

    public ChannelJoiner(Channel channel, User user) {
        this.channel = channel;
        this.user = user;
        this.isShow = true;
    }

}
