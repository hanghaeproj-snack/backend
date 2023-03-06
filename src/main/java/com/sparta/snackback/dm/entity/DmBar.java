package com.sparta.snackback.dm.entity;

import com.sparta.snackback.common.entity.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DmBar extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dmId;

    private String title;

    private String username;

    public  DmBar (String title){
        this.title = title;
    }

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;

}
