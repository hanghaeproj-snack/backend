package com.sparta.snackback.user.service;

import com.sparta.snackback.user.dto.*;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.entity.UserRoleEnum;
import com.sparta.snackback.jwt.JwtUtil;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public StatusMsgResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        String nickname = signupRequestDto.getNickname();

        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호 오류로 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(email, password, nickname, role);
        userRepository.save(user);

        StatusMsgResponseDto statusMsgResponseDto = new StatusMsgResponseDto("회원가입 완료", HttpStatus.OK);
        return statusMsgResponseDto;
    }

    // 로그인
    @Transactional(readOnly = true)
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 이메일이 아닙니다.")
        );

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));

        LoginResponseDto loginResponseDto = new LoginResponseDto(user);
        return loginResponseDto;
    }

    // 중복확인
    @Transactional(readOnly = true)
    public void emailCheck(LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();

        Optional<User> findEmail = userRepository.findByEmail(email);

        if (findEmail.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ProfileDto> getProfile(User user) {

        User info = userRepository.findById(user.getId()).orElseThrow(
                ()-> new IllegalArgumentException("없는 유저 입니다.")
        );

        ProfileDto profileDto = new ProfileDto(info);

        return ResponseEntity.ok().body(profileDto);
    }



}