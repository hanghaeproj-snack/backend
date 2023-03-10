package com.sparta.snackback.user.dto;

import lombok.Getter;
@Getter
public class SignupRequestDto {
    private String email;
    private String password;
    private String nickname;
    private boolean admin = false;
    private String adminToken = "";
}