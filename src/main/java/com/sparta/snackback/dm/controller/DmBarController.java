package com.sparta.snackback.dm.controller;

import com.sparta.snackback.dm.entity.DmBar;
import com.sparta.snackback.dm.repository.DmBarRepository;
import com.sparta.snackback.dm.service.DmBarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class DmBarController {

    private final DmBarRepository dmBarRepository;
    private final DmBarService dmBarService;

    //dm 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    //모든 dm 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<DmBar> room() {
        return dmBarService.findAllDm();
    }

    //dm 생성
    @PostMapping("/room")
    @ResponseBody
    public DmBar createRoom(@RequestParam String title) {
        return dmBarService.createDm(title);
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
    public Optional<DmBar> roomInfo(@PathVariable Long roomId) {
        return dmBarService.findById(roomId);
    }

}
