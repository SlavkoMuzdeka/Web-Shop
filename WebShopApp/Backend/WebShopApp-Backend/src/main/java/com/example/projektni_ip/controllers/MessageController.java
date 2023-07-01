package com.example.projektni_ip.controllers;

import com.example.projektni_ip.entities.dto.Message;
import com.example.projektni_ip.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Map<String, Object> insertMessage(@RequestBody Message message) {
        return messageService.insert(message);
    }
}
