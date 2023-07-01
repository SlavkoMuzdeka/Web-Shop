package com.example.projektni_ip.services;

import com.example.projektni_ip.entities.dto.SingleAttribute;
import com.example.projektni_ip.entities.dto.SingleCategory;

import java.util.List;

public interface CategoryService {

    List<SingleCategory> findAll();

    List<Integer> getAllChildrenIds(Integer id);

    List<SingleAttribute> findAllAttributes(Integer id);
}
