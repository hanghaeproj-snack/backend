package com.sparta.snackback.user.repository;

import com.sparta.snackback.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // 회원가입, 로그인 모두에서 사용 (중복확인)
}