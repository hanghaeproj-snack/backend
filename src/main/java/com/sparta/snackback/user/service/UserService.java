package com.sparta.snackback.user.service;

import com.sparta.snackback.common.dto.SendMessageDto;
import com.sparta.snackback.common.util.SuccessCode;
import com.sparta.snackback.exception.CustomException;
import com.sparta.snackback.exception.ErrorCode;
import com.sparta.snackback.image.service.S3Uploader;
import com.sparta.snackback.user.dto.*;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.entity.UserRoleEnum;
import com.sparta.snackback.security.jwt.JwtUtil;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;

    // ADMIN_TOKEN
    @Value("${admin.token}")
    private String ADMIN_TOKEN;

    // 회원가입
    @Transactional
    public ResponseEntity<SendMessageDto> signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        String nickname = signupRequestDto.getNickname();

        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new CustomException(ErrorCode.WRONG_ADMIN_TOKEN);
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(email, password, nickname, role);
        userRepository.save(user);

        return SendMessageDto.toResponseEntity(SuccessCode.SIGNUP_SUCCESS);
    }

    // 로그인
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.UNREGISTER_USER)
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.WRONG_PASSWORD);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));

        return new LoginResponseDto(user);
    }

    // 중복확인
    @Transactional(readOnly = true)
    public ResponseEntity<SendMessageDto> emailCheck(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();

        Optional<User> findEmail = userRepository.findByEmail(email);

        if (findEmail.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        return SendMessageDto.toResponseEntity(SuccessCode.CHECKUP_SUCCESS);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ProfileDto> getProfile(User user) {

        User info = userRepository.findById(user.getId()).orElseThrow(
                ()-> new IllegalArgumentException("없는 유저 입니다.")
        );

        ProfileDto profileDto = new ProfileDto(info);

        return ResponseEntity.ok().body(profileDto);
    }


    @Transactional
    public ResponseEntity<ProfileDto> changeProfile(MultipartFile image, String nickname, User user) throws IOException {

        String storedFileName = s3Uploader.upload(image,"profile");
        ProfileDto profileDto =new ProfileDto(nickname,storedFileName);

        userRepository.findById(profileDto.getImage(), profileDto.getNickname(), user.getId());

        return ResponseEntity.ok().body(profileDto);
    }
}