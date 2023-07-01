package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class CategoryParent {

    private int id;

    private CategoryParent category;
}
