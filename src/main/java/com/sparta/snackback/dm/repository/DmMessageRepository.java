package com.sparta.snackback.dm.repository;

import com.sparta.snackback.dm.entity.DMMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DmMessageRepository extends JpaRepository<DMMessage,Long> {

//    List<DMMessage> findByDmBarOrderByCreatedAtDesc(Long dmId);
}
