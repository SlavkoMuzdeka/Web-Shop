package com.example.projektni_ip.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_attribute")
public class ProductAttributeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "attribute")
    private String attribute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_id", referencedColumnName = "id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ATTRIBUTE_id", referencedColumnName = "id", nullable = false)
    private AttributeEntity attributeEnt;
}
