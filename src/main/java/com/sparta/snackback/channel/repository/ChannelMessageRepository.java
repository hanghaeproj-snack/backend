package com.sparta.snackback.channel.repository;

import com.sparta.snackback.channel.entity.ChannelMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelMessageRepository extends JpaRepository<ChannelMessage, Long> {
}
