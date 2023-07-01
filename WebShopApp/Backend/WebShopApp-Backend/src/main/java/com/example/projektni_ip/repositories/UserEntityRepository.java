package com.example.projektni_ip.repositories;

import com.example.projektni_ip.entities.UserEntity;
import com.example.projektni_ip.exceptions.UnauthorizedException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findByUserName(String userName) throws UnauthorizedException;
}
