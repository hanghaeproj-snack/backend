package com.sparta.snackback.dm.service;

import com.sparta.snackback.dm.dto.DmMessageDto;
import com.sparta.snackback.dm.entity.DMMessage;
import com.sparta.snackback.dm.repository.DmMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DmMessageService {

    private final SimpMessageSendingOperations sendingOperations;
    private final DmMessageRepository dmMessageRepository;

    public void sendDmMessage(DmMessageDto message){

        if(message.getType().equals(DmMessageDto.MessageType.ENTER)){
            message.setMessage(message.getUser() + "님이 입장하셨습니다.");
        }

        DMMessage dmMessage = dmMessageRepository.saveAndFlush(new DMMessage(message));
//        sendingOperations.convertAndSend("/topic/chat/room" + message.getDmId(),message);
    }

    public List<DmMessageDto> getMessages(Long dmId) {
//        List<DMMessage> DMMessageList = dmMessageRepository.findByDmBarOrderByCreatedAtDesc(dmId);
        List<DmMessageDto> dmMessageDtoList = new ArrayList<>();

//        for (DMMessage message : DMMessageList) {
//            dmMessageDtoList.add(new DmMessageDto(message));
////            log.info("message 불러오기 : " + message.getDmUser() +" : "+message.getDmMessage() );
//        }

        return dmMessageDtoList;
    }

    //끝
}
