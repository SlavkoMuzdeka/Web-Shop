package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class ProductAttribute {

    private Integer id;

    private String attribute;

    private Attribute attributeEnt;

    private Product product;
}
