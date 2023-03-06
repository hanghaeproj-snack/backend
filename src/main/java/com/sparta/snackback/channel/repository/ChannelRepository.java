package com.sparta.snackback.channel.repository;

import com.sparta.snackback.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
