package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "city")
    private String city;

    @Basic
    @Column(name = "user_name")
    private String userName;

    @Basic
    @Column(name = "password")
    private String password;

    @Basic
    @Column(name = "avatar")
    private byte[] avatar;

    @Basic
    @Column(name = "mail")
    private String mail;

    @Basic
    @Column(name = "is_activated")
    private Boolean isActivated;

    @Basic
    @Column(name = "status")
    private Boolean status;

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private List<MessageEntity> messages;

    @OneToMany(mappedBy = "seller")
    private List<ProductEntity> soldProducts;

    @OneToMany(mappedBy = "buyer")
    private List<ProductEntity> boughtProducts;
}
