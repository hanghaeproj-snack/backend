//package com.sparta.snackback.dm.controller;
//
//import com.sparta.snackback.dm.dto.DmMessageDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class DmStompMessageController {
//
////    private final SimpMessagingTemplate template;
////
////
////    @MessageMapping(value = "/chat/enter")
////    public void enter(DmMessageDto message) {
////
////        message.setMessage(message.getUserId() + "님이 디엠에 참여하셨습니다");
////        template.convertAndSend("/sub/chat/room" + message.getDmId(),message);
////
////    }
////
////    @MessageMapping(value = "chat/message")
////    public void message(DmMessageDto message) {
////
////        template.convertAndSend("/sub/chat/room" + message.getDmId(),message);
////    }
//}
