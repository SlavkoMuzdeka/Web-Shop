package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.entities.MessageEntity;
import com.example.projektni_ip.entities.UserEntity;
import com.example.projektni_ip.entities.dto.Message;
import com.example.projektni_ip.repositories.MessageEntityRepository;
import com.example.projektni_ip.services.MessageService;
import com.example.projektni_ip.services.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageEntityRepository repository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    public MessageServiceImpl(MessageEntityRepository repository, UserService userService, ModelMapper modelMapper) {
        this.repository = repository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Map<String, Object> insert(Message message) {
        UserEntity userEntity = userService.findById(message.getUser().getId());
        MessageEntity messageEntity = modelMapper.map(message, MessageEntity.class);
        messageEntity.setUser(userEntity);
        messageEntity.setStatus(false);
        repository.saveAndFlush(messageEntity);
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("status_code", 200);
        return response;
    }
}
