package com.sparta.snackback.user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StatusMsgResponseDto {
    private String message;
    private HttpStatus httpStatus;

    // 생성자
    public StatusMsgResponseDto(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}