package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class Product {
    protected Integer id;
    protected String title;
    protected Double price;
    protected Image image;
    protected Boolean isFinished;
}
