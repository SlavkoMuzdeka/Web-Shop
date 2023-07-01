package com.example.projektni_ip.entities.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class SingleProduct extends Product {

    private String description;

    private Object type;

    private String location;

    private String contact;

    private Date creationDate;

    private Boolean isActive;

    private Boolean isSold;

    private List<Comment> comments;

    private List<Image> images;

    private List<ProductAttribute> productAttributes;

    private User seller;

    private Category category;
}
