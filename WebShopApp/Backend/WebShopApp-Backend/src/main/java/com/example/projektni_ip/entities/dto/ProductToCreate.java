package com.example.projektni_ip.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductToCreate {

    private String title;

    private String description;

    private Double price;

    private String location;

    private String contact;

    private Object type;

    private List<Image> images;

    private List<AttributeUserInput> attributes;

    private User seller;

    private Integer category;
}
