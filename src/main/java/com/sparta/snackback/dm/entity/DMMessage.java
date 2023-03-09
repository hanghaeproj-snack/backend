package com.sparta.snackback.dm.entity;

import com.sparta.snackback.common.entity.Timestamped;
import com.sparta.snackback.dm.dto.DmMessageDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DMMessage extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    private boolean isShow;

    @ManyToOne
    @JoinColumn(name = "dm_id")
    private DM dm;

    @ManyToOne
    @JoinColumn(name="dm_joiner_id")
    private DMJoiner dmJoiner;

    public DMMessage(DmMessageDto dmMessageDto) {
        this.contents = dmMessageDto.getInputMsg();

    }


}
