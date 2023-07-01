package com.example.projektni_ip.repositories;

import com.example.projektni_ip.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Integer> {
}
