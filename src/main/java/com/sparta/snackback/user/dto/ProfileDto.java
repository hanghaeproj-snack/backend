package com.sparta.snackback.user.dto;

import com.sparta.snackback.user.entity.User;
import lombok.Getter;

@Getter
public class ProfileDto {

    private String email;
    private String nickname;
    private String image;

    public ProfileDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.image = user.getImage();
    }
}
