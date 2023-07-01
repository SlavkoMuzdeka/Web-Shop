package com.example.projektni_ip.repositories;

import com.example.projektni_ip.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageEntityRepository extends JpaRepository<MessageEntity, Integer> {
}
