package com.sparta.snackback.dm.controller;

import com.sparta.snackback.dm.dto.DMDto;
import com.sparta.snackback.dm.entity.DM;
import com.sparta.snackback.dm.service.DmBarService;
import com.sparta.snackback.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/dm")
@Log4j2
public class DmBarController {

    private final DmBarService dmBarService;

//    //dm 리스트 화면
//    @GetMapping("/room")
//    public String rooms(Model model) {
//        return "/chat/room";
//    }

    //DM 바 조회 >> 로그인한 유저가 속해있는 모든 DM 조회
    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<DMDto>> getAllDm(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return dmBarService.getAllDm(userDetails.getUser());
    }

    //dm 생성
    @PostMapping("/invite")
    @ResponseBody
    public ResponseEntity<DMDto> createDM(@RequestBody List<Long> userList) {

        return dmBarService.createDm(userList);
    }

    //dm 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable Long roomId) {

        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }

    //특정 dm 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public Optional<DM> roomInfo(@PathVariable Long roomId) {
        return dmBarService.findById(roomId);
    }

}
