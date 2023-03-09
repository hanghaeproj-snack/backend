package com.sparta.snackback.dm.controller;

import com.sparta.snackback.dm.dto.DmMessageDto;
import com.sparta.snackback.dm.repository.DmMessageRepository;
import com.sparta.snackback.dm.service.DmMessageService;
import com.sparta.snackback.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DmMessageController {

    private final SimpMessageSendingOperations sendingOperations;
    private final DmMessageService dmMessageService;

    @MessageMapping("/chat/message")
    public void enter(DmMessageDto message) {
        log.info(message.getNickname() + " : " + message.getMessage());

        DmMessageDto dmMessageDto = dmMessageService.sendDmMessage(message);


        sendingOperations.convertAndSend("/sub/topic/dm/message/" + message.getUuid(), dmMessageDto);
    }

    //예전 기록 조회
    @GetMapping("/api/dm/message")
    public ResponseEntity<List<DmMessageDto>> getMessages(@RequestParam Long id){
        return dmMessageService.getMessages(id);
    }

}
