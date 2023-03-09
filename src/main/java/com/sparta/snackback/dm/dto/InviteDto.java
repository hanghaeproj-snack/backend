package com.sparta.snackback.dm.dto;


import com.sparta.snackback.user.entity.User;
import lombok.Getter;

@Getter
public class InviteDto {
    private Long id;
    private String nickname;

    public InviteDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
    }
}
