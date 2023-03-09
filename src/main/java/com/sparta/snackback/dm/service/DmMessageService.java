package com.sparta.snackback.dm.service;

import com.sparta.snackback.dm.dto.DmMessageDto;
import com.sparta.snackback.dm.entity.DM;
import com.sparta.snackback.dm.entity.DMMessage;
import com.sparta.snackback.dm.repository.DMRepository;
import com.sparta.snackback.dm.repository.DmMessageRepository;
import com.sparta.snackback.exception.CustomException;
import com.sparta.snackback.exception.ErrorCode;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final UserRepository userRepository;

    private final DMRepository dmRepository;

    public DmMessageDto sendDmMessage(DmMessageDto message){

//        if(message.getType().equals(DmMessageDto.MessageType.ENTER)){
//            message.setMessage(message.getNickname() + "님이 입장하셨습니다.");
//        }

        DM dm = dmRepository.findById(message.getDmId()).orElseThrow(
                ()-> new IllegalArgumentException("디엠 없음")
        );

        User user = userRepository.findByEmail(message.getEmail()).orElseThrow(
                ()-> new IllegalArgumentException("없는 유저임")
        );

        DMMessage dmMessage = dmMessageRepository.saveAndFlush(new DMMessage(message,dm,user));

        Long id = dmMessage.getId();
        message.setId(id);

        return new DmMessageDto(dmMessage, user);
    }

    public ResponseEntity<List<DmMessageDto>> getMessages(Long id) {
        List<DMMessage> DMMessageList = dmMessageRepository.findAllByDm_IdOrderByCreatedAtAsc(id);
        List<DmMessageDto> dmMessageDtoList = new ArrayList<>();

        for (DMMessage dmMessage : DMMessageList){
            User user = userRepository.findById(dmMessage.getUser().getId()).orElseThrow(
                    ()-> new CustomException(ErrorCode.UNREGISTER_USER)
            );

            dmMessageDtoList.add(new DmMessageDto(dmMessage,user));
        }


        return ResponseEntity.ok().body(dmMessageDtoList);
    }

    //끝
}
