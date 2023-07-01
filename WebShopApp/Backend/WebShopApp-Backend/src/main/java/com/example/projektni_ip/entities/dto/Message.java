package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class Message {

    private Integer id;

    private String title;

    private String content;

    private User user;
}
