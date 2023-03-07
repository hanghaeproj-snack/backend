package com.sparta.snackback.user.dto;

import com.sparta.snackback.user.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {// 로그인 -> body에 반환
    private String email;
    private String nickname;

    public LoginResponseDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}