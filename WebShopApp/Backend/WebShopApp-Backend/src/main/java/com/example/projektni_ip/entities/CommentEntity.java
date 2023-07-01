package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "text")
    private String text;

    @Basic
    @Column(name = "creation_date")
    private String creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

}
