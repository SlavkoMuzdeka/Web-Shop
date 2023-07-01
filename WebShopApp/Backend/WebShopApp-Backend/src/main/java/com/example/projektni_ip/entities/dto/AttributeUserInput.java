package com.example.projektni_ip.entities.dto;

import lombok.Data;

@Data
public class AttributeUserInput {

    protected Integer id;

    protected String name;

    protected String userInput;

    protected Object type;

    protected Category category;
}
