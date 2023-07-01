package com.example.projektni_ip.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categories")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CategoryEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")
    private List<AttributeEntity> attributes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIES_id", referencedColumnName = "id")
    @JsonBackReference
    private CategoryEntity category;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<CategoryEntity> categories;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
