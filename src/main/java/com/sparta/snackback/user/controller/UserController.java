package com.sparta.snackback.user.controller;

import com.sparta.snackback.security.UserDetailsImpl;
import com.sparta.snackback.user.dto.*;
import com.sparta.snackback.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/auth/signup")
    public StatusMsgResponseDto signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/auth/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    // 중복확인
    @PostMapping("/auth/check")
    public StatusMsgResponseDto emailCheck(@RequestBody LoginRequestDto loginRequestDto) {
        userService.emailCheck(loginRequestDto);
        return new StatusMsgResponseDto("이메일 중복확인 완료", HttpStatus.OK);
    }

    //내 프로필 조회
    @GetMapping("/user/profile")
    public ResponseEntity<ProfileDto> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getProfile(userDetails.getUser());
    }

}