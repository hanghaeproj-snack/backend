package com.sparta.snackback.user.entity;

import com.sparta.snackback.user.dto.ProfileDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private String image;

    // 생성자 -> 값 초기화
    public User(String email, String password, String nickname, UserRoleEnum role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void update(ProfileDto profileDto){
        this.image = profileDto.getImage();
        this.nickname = profileDto.getNickname();
    }
}
