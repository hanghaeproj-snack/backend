package com.sparta.snackback.user.repository;

import com.sparta.snackback.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE users SET image = ?1, nickname = ?2 WHERE id = ?3 ")
    int findById(String image, String nickname, Long id);
}