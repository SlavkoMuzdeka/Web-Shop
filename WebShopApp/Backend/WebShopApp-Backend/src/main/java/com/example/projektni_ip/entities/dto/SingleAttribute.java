package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class SingleAttribute extends Attribute {

    protected Object type;

    protected Category category;
}
