package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "attributes")
public class AttributeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "type")
    private Object type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "attributeEnt")
    private List<ProductAttributeEntity> productAttributes;
}
