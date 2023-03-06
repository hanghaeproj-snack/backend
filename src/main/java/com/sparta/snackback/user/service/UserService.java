package com.sparta.snackback.user.service;

import com.sparta.snackback.user.dto.LoginRequestDto;
import com.sparta.snackback.user.dto.SignupRequestDto;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.entity.UserRoleEnum;
import com.sparta.snackback.user.jwt.JwtUtil;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();

        // 이메일 중복확인
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        String nickname = signupRequestDto.getNickname();

        // 관리자권한
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) { // Admin true
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호 오류로 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN; // 관리자권한 전환
        }
        User user = new User(email, password, nickname, role);
        userRepository.save(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        // 이메일 확인
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("등록된 이메일이 아닙니다.")
        );

        // 비밀번호 확인
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getEmail(), user.getRole()));
    }
}
