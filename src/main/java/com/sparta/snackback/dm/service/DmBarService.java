package com.sparta.snackback.dm.service;

import com.sparta.snackback.dm.dto.DMDto;
import com.sparta.snackback.dm.dto.InviteDto;
import com.sparta.snackback.dm.entity.DM;
import com.sparta.snackback.dm.entity.DMJoiner;
import com.sparta.snackback.dm.repository.DMJoinerRepository;
import com.sparta.snackback.dm.repository.DMRepository;
import com.sparta.snackback.user.entity.User;
import com.sparta.snackback.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class DmBarService {

    private final DMRepository DMRepository;
    private final DMJoinerRepository dmJoinerRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    //디엠 바 조회
    public ResponseEntity<List<DMDto>> getAllDm(User user) {

        List<DMJoiner> dmJoinerList = dmJoinerRepository.findAllByUserId(user.getId());
        List<DMDto> dmDtoList = new ArrayList<>();

        for(DMJoiner dmJoiner : dmJoinerList){
            StringBuilder title = new StringBuilder();

            DM dm = DMRepository.findById(dmJoiner.getDm().getId()).orElseThrow(
                    ()-> new IllegalArgumentException("없는 디엠입니당"));

            List<DMJoiner> dmUserList = dmJoinerRepository.findAllByDmId(dm.getId());

            for(int i = 0; i < dmUserList.size(); i++) {
                String nickname = userRepository.findById(dmUserList.get(i).getUser().getId()).orElseThrow(
                        ()-> new IllegalArgumentException("없는 유저입니다.")).getNickname();

                if (i == dmUserList.size() - 1) {
                    title.append(nickname);
                } else {
                    title.append(nickname).append(", ");
                }
            }

            dmDtoList.add(new DMDto(dm, title.toString()));
        }

        return ResponseEntity.ok().body(dmDtoList);
    }

    //디엠방 하나 불러오기
    public Optional<DM> findById(Long dmId){
        return DMRepository.findById(dmId);
    }


    //디엠 생성
    @Transactional
    public ResponseEntity<DMDto> createDm(List<Long> userList) {

        String uuid = UUID.randomUUID().toString();

        DM dm = DMRepository.saveAndFlush(new DM(uuid));


        for(Long userId : userList){
            User user = userRepository.findById(userId).orElseThrow(
                    ()-> new IllegalArgumentException("유효하지 않은 사용자입니다.")
            );

            DMJoiner dmJoiner = dmJoinerRepository.saveAndFlush(new DMJoiner(user,dm));
        }

        return ResponseEntity.ok().body(DMDto.of(dm));

    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<InviteDto>> invite() {
        List<User> userList = userRepository.findAll();

        List<InviteDto> inviteDtoList = new ArrayList<>();
        for (User user : userList) {
            System.out.println("user.getId = " + user.getId());
            System.out.println("user.getNickname = " + user.getNickname());
            inviteDtoList.add(new InviteDto(user));
        }
        return ResponseEntity.ok().body(inviteDtoList);
    }
}
