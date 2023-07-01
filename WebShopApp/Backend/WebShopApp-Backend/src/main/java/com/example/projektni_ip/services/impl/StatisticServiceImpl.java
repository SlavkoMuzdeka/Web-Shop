package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.entities.StatisticEntity;
import com.example.projektni_ip.repositories.StatisticEntityRepository;
import com.example.projektni_ip.services.StatisticService;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticEntityRepository repository;

    public StatisticServiceImpl(StatisticEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void insertLog(StatisticEntity entity) {
        repository.saveAndFlush(entity);
    }
}
