package com.sparta.snackback.channel.repository;

import com.sparta.snackback.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Optional<Channel> findByTitle(String title);
    Optional<Channel> findByUuid(String roomId);
}
