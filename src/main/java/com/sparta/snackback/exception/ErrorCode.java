package com.sparta.snackback.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    DUPLICATE_EMAIL(BAD_REQUEST, "duplicate email"),
    UNREGISTER_USER(BAD_REQUEST, "unregister user"),
    WRONG_PASSWORD(BAD_REQUEST, "wrong password"),
    WRONG_ADMIN_TOKEN(BAD_REQUEST, "wrong admin token"),
    DUPLICATE_TITLE(BAD_REQUEST, "duplicate title"),
    WRONG_CHANNEL(BAD_REQUEST, "wrong channel"),


    /* 401 UNAUTHORIZED : 인증 실패 */
    INVALID_TOKEN(UNAUTHORIZED, "invalid token"),
    NULL_TOKEN(UNAUTHORIZED, "null token"),

    /* 403 FORBIDDEN : 인가 실패 */
    PERMISSION_DINED(FORBIDDEN, "forbidden");


    private final HttpStatus httpStatus;
    private final String message;

}