package com.example.projektni_ip.services;

import com.example.projektni_ip.entities.dto.Comment;

public interface CommentService {

    Comment insert(Integer productId, Comment comment);
}
