package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String firstName;

    private String lastName;

    private byte[] avatar;

    private String mail;

    private String city;

    private String password;

    private String userName;

}
