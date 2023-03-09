package com.sparta.snackback.user.controller;

import com.sparta.snackback.common.dto.SendMessageDto;
import com.sparta.snackback.security.user.UserDetailsImpl;
import com.sparta.snackback.user.dto.*;
import com.sparta.snackback.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ResponseEntity<SendMessageDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    // 로그인
    @PostMapping("/auth/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    // 중복확인
    @PostMapping("/auth/check")
    public ResponseEntity<SendMessageDto> emailCheck(@RequestBody LoginRequestDto loginRequestDto) {
        return userService.emailCheck(loginRequestDto);
    }

    //내 프로필 조회
    @GetMapping("/user/profile")
    public ResponseEntity<ProfileDto> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getProfile(userDetails.getUser());
    }

    //내 프로필 수정
    @PatchMapping("/user/profile")
    public ResponseEntity<ProfileDto> changeProfile(@RequestPart MultipartFile image, @RequestPart String nickname  ,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        return userService.changeProfile(image,nickname, userDetails.getUser());
    }

}