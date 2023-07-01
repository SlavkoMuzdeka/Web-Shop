package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class Comment {
    private Integer id;

    private String text;

    private String creationDate;

    private User user;

    private Product product;
}
