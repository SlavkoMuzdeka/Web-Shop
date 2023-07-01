package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "messages")
public class MessageEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;


    @Basic
    @Column(name = "title")
    private String title;


    @Basic
    @Column(name = "content")
    private String content;


    @Basic
    @Column(name = "status")
    private Boolean status;


    @ManyToOne
    @JoinColumn(name = "USER_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;
}
