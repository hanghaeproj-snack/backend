package com.sparta.snackback.dm.repository;

import com.sparta.snackback.dm.entity.DmBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DmBarRepository extends JpaRepository<DmBar, Long> {

    //desc 인지 asc 인지 확실 하지 않음. 결과 값을 확인해 볼 것!
    List<DmBar> findAllByOrderByCreatedAtDesc();
}
