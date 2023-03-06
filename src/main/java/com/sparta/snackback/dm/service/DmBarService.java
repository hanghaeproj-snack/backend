package com.sparta.snackback.dm.service;

import com.sparta.snackback.dm.entity.DmBar;
import com.sparta.snackback.dm.repository.DmBarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DmBarService {

    private Map<String, DmBar> dmBars;
    private final DmBarRepository dmBarRepository;

//    @PostConstruct //의존성 주입 후 실행되는 코드
//    private void init() {
//        dmBars = new LinkedHashMap<>();
//    }

    //디엠 바 불러오기
    public List<DmBar> findAllDm() {

        //디엠 최근 생성 순으로 반환
        List<DmBar> result = dmBarRepository.findAllByOrderByCreatedAtDesc();

        return result;
    }

    //디엠방 하나 불러오기
    public Optional<DmBar> findById(Long dmId){
        return dmBarRepository.findById(dmId);
    }

    //디엠 생성
    public DmBar createDm(String title) {

        DmBar dmBar = dmBarRepository.saveAndFlush(new DmBar(title));

        return dmBar;

    }

}
