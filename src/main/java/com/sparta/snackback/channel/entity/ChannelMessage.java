package com.sparta.snackback.channel.entity;

import com.sparta.snackback.channel.dto.ChannelChatMessageDto;
import com.sparta.snackback.common.entity.Timestamped;
import com.sparta.snackback.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class ChannelMessage extends Timestamped {

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
    private String contents;

    @Column(nullable = false)
    private Boolean isShow;

    public ChannelMessage(Channel channel, User user, ChannelChatMessageDto.Publisher publisher) {
        this.channel = channel;
        this.user = user;
        this.contents = publisher.getMessage();
        this.isShow = true;
    }

}
