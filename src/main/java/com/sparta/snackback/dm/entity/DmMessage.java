package com.sparta.snackback.dm.entity;

import com.sparta.snackback.common.entity.Timestamped;
import com.sparta.snackback.dm.dto.DmMessageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
//@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class DmMessage extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long dmId;


//    public enum MessageType{
//        ENTER,TALK
//    }

//    private MessageType type;

//    private Long dmId;
    private String dmUser;
    private String dmMessage;

//    @ManyToOne
//    @JoinColumn(name="dm_id")
    private Long dmBar;

    public DmMessage(DmMessageDto dmMessageDto){
        this.dmUser = dmMessageDto.getUser();
        this.dmMessage = dmMessageDto.getMessage();
        this.dmBar = dmMessageDto.getDmId();
    }


}
