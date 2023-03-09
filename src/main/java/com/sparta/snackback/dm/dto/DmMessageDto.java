package com.sparta.snackback.dm.dto;

import com.sparta.snackback.dm.entity.DMMessage;
import com.sparta.snackback.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DmMessageDto {


//        public enum MessageType{
//        ENTER,TALK
//    }
//
//    private MessageType type;

    private Long dmId;
    private String email;
    private String nickname;
    private String image;
    private String message;
    private String uuid;
    private Long id;

    public DmMessageDto(DMMessage message, User user) {
        this.id = message.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.image = user.getImage();
        this.message = message.getContents();
    }
    
}
