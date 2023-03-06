package com.sparta.snackback.channel.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
}
