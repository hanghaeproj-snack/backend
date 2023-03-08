package com.sparta.snackback.dm.entity;


import com.sparta.snackback.common.entity.Timestamped;
import com.sparta.snackback.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DMJoiner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "dm_id")
    private DM dm;

    public DMJoiner(User user, DM dm) {
        this.user = user;
        this.dm = dm;
    }
}
