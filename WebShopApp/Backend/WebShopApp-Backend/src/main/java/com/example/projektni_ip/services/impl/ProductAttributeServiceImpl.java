package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.services.ProductAttributeService;
import com.example.projektni_ip.entities.ProductAttributeEntity;
import com.example.projektni_ip.repositories.ProductAttributeEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    private final ProductAttributeEntityRepository repository;

    public ProductAttributeServiceImpl(ProductAttributeEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductAttributeEntity insert(ProductAttributeEntity productAttributeService) {
        return repository.saveAndFlush(productAttributeService);
    }
}
