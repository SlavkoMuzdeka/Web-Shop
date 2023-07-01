package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.entities.dto.SingleAttribute;
import com.example.projektni_ip.repositories.AttributeEntityRepository;
import com.example.projektni_ip.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    private final AttributeEntityRepository repository;

    private final ModelMapper modelMapper;

    public AttributeServiceImpl(AttributeEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<SingleAttribute> findAll() {
        return repository.findAll().stream().map(a -> modelMapper.map(a, SingleAttribute.class)).toList();
    }
}
