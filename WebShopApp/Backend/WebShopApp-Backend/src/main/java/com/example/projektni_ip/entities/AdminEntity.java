package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admins")
public class AdminEntity {
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
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "is_admin")
    private Boolean isAdmin;

}
