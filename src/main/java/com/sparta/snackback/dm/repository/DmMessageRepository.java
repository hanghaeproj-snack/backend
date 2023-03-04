package com.sparta.snackback.dm.repository;

import com.sparta.snackback.dm.entity.DmMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmMessageRepository extends JpaRepository<DmMessage,Long> {


}
