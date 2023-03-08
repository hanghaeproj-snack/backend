package com.sparta.snackback.dm.dto;

import com.sparta.snackback.dm.entity.DM;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class DMDto {

    private Long id;

    private String title;

    private String uuid;

//    private Set<WebSocketSession> sessionSet = new HashSet<>();

    @Builder
    public DMDto(DM dm) {
        this.id = dm.getId();
        this.uuid = dm.getUuid();
    }

    public static DMDto of(DM dm){
        return DMDto.builder()
                .dm(dm)
                .build();
    }

}
