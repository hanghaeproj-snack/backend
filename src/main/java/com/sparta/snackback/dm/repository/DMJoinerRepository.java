package com.sparta.snackback.dm.repository;

import com.sparta.snackback.dm.entity.DMJoiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DMJoinerRepository extends JpaRepository<DMJoiner,Long> {

//    @Query("select dm from DMJoiner where user = :userId")
    List<DMJoiner> findAllByUserId(Long userId);

    List<DMJoiner> findAllByDmId(Long dmId);

}
