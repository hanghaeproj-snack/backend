package com.sparta.snackback.dm.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class DmBarDto {

    private Long dmId;
    private String title;
    private Set<WebSocketSession> sessionSet = new HashSet<>();

    public static DmBarDto creat(String title){
        DmBarDto dmBarDto = new DmBarDto();

//        dmBarDto.dmId = UUID.randomUUID().toString();
//        this.dmId =
        dmBarDto.title = title;
        return dmBarDto;

    }
}
