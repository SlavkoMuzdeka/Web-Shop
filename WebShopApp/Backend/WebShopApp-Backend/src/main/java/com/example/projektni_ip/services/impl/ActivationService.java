package com.example.projektni_ip.services.impl;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ActivationService {

    private static final Map<Object, Integer> users = new HashMap<>();

    public void addUser(Object id, String username) {
        Integer pin = generatePin();
        users.put(id, pin);
        users.put(username, pin);
    }

    public void deleteUser(Object id){
        users.remove(id);
    }

    public boolean containKey(Object id){
        return users.containsKey(id);
    }

    public Integer getPin(Object id) {
        return users.get(id);
    }

    private Integer generatePin() {
        return ThreadLocalRandom.current().nextInt(1000, 10000);
    }
}
