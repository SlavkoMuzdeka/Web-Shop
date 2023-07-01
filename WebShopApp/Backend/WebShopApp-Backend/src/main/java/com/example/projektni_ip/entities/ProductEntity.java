package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "price")
    private Double price;

    @Basic
    @Column(name = "type")
    private Object type;

    @Basic
    @Column(name = "location")
    private String location;

    @Basic
    @Column(name = "contact")
    private String contact;

    @Basic
    @Column(name = "creation_date")
    private Date creationDate;

    @Basic
    @Column(name = "is_active")
    private Boolean isActive;

    @Basic
    @Column(name = "is_sold")
    private Boolean isSold;

    @Basic
    @Column(name = "is_finished")
    private Boolean isFinished;

    @OneToMany(mappedBy = "product")
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "product")
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "product")
    private List<ProductAttributeEntity> productAttributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller", referencedColumnName = "id", nullable = false)
    private UserEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer", referencedColumnName = "id")
    private UserEntity buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIES_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;
}
