package com.sparta.snackback.dm.entity;

import com.sparta.snackback.common.entity.Timestamped;
import com.sparta.snackback.dm.dto.DmMessageDto;
import com.sparta.snackback.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DMMessage extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private boolean isShow;

    @ManyToOne
    @JoinColumn(name = "dm_id")
    private DM dm;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public DMMessage(DmMessageDto dmMessageDto, DM dm, User user) {
        this.contents = dmMessageDto.getMessage();
        this.dm = dm;
        this.user = user;

    }


}
