package com.sparta.snackback.user.dto;

import lombok.Getter;
@Getter
public class LoginRequestDto { // 로그인 -> body에 반환
    private String email;
    private String password;
}
