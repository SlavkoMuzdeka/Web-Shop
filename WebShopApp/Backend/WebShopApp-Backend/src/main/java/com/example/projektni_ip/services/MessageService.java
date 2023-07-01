package com.example.projektni_ip.services;

import com.example.projektni_ip.entities.dto.Message;

import java.util.Map;

public interface MessageService {
    Map<String, Object> insert(Message message);
}
