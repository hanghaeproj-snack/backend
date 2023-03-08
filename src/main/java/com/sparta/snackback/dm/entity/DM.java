package com.sparta.snackback.dm.entity;

import com.sparta.snackback.common.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DM extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = false, unique = true)
    private String uuid;


    public DM(String uuid) {
        this.uuid = uuid;
    }
}
