package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.services.ImageService;
import com.example.projektni_ip.entities.ImageEntity;
import com.example.projektni_ip.repositories.ImageEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageEntityRepository repository;

    public ImageServiceImpl(ImageEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public ImageEntity insert(ImageEntity image) {
        return repository.saveAndFlush(image);
    }
}
