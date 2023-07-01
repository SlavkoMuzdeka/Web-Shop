package com.example.projektni_ip.services;

import com.example.projektni_ip.entities.UserEntity;
import com.example.projektni_ip.entities.dto.User;
import com.example.projektni_ip.entities.dto.UserActivate;
import com.example.projektni_ip.exceptions.NotFoundException;
import com.example.projektni_ip.exceptions.UnauthorizedException;

import java.util.Map;

public interface UserService {

    UserEntity findById(Integer id) throws NotFoundException;

    Map<String, Object> authenticate(String username, String password) throws UnauthorizedException;

    Map<String, Object> update(Integer userId, User user) throws  NotFoundException;

    Map<String, Object> insert(User user);

    Map<String, Object> activateUser(UserActivate userActivate) throws NotFoundException;

}
