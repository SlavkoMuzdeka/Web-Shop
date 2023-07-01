package com.example.projektni_ip.services.impl;

import com.example.projektni_ip.services.UserService;
import com.example.projektni_ip.entities.UserEntity;
import com.example.projektni_ip.entities.dto.User;
import com.example.projektni_ip.entities.dto.UserActivate;
import com.example.projektni_ip.exceptions.NotFoundException;
import com.example.projektni_ip.exceptions.UnauthorizedException;
import com.example.projektni_ip.repositories.UserEntityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserEntityRepository repository;

    private final ModelMapper modelMapper;

    private final ActivationService activationService;

    private final MailService mailService;

    @PersistenceContext
    private EntityManager entityManager;

    public UserServiceImpl(UserEntityRepository repository, ModelMapper modelMapper, ActivationService activationService, MailService mailService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.activationService = activationService;
        this.mailService = mailService;
    }

    @Override
    public UserEntity findById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Map<String, Object> authenticate(String username, String password) throws UnauthorizedException {
        UserEntity userEntity = repository.findByUserName(username);
        Map<String, Object> response = new HashMap<>();
        if(userEntity != null && activationService.containKey(userEntity.getId())){
            activationService.deleteUser(userEntity.getId());
        }
        if (userEntity == null || !userEntity.getPassword().equals(password) || !userEntity.getStatus()) {
            response.put("user", null);
            response.put("status", false);
            response.put("status_code", 401);
            return response;
        }else if(!userEntity.getIsActivated()){
            response.put("status", true);
            response.put("status_code", 400);
            activationService.addUser(userEntity.getId(), userEntity.getUserName());
            mailService.sendPinEmail(userEntity.getMail(), activationService.getPin(userEntity.getId()));
        }else {
            User user = modelMapper.map(userEntity, User.class);
            response.put("user", user);
            response.put("status", true);
            response.put("status_code", 200);
        }
        return response;
    }

    @Override
    public Map<String, Object> update(Integer userId, User user) throws NotFoundException {
        UserEntity userEntity = repository.findById(userId).orElseThrow(NotFoundException::new);
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setCity(user.getCity());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(user.getPassword());
        userEntity.setAvatar(user.getAvatar());
        userEntity = repository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);

        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("status_code", 200);
        response.put("user", modelMapper.map(userEntity, User.class));
        return response;
    }


    @Override
    public Map<String, Object> insert(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setIsActivated(false);
        userEntity.setStatus(true);
        userEntity.setIsActivated(false);
        userEntity = repository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);

        activationService.addUser(userEntity.getId(), userEntity.getUserName());
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("status_code", 201);
        response.put("user_id", userEntity.getId());
        mailService.sendPinEmail(userEntity.getMail(), activationService.getPin(userEntity.getId()));
        return response;
    }

    @Override
    public Map<String, Object> activateUser(UserActivate userActivate) throws NotFoundException {
        Map<String, Object> result = new HashMap<>();
        UserEntity entity = null;
        if(userActivate.getValue() instanceof String){
            entity = repository.findByUserName((String)userActivate.getValue());
        }else if(userActivate.getValue() instanceof Integer){
            entity = repository.findById((Integer)userActivate.getValue()).orElseThrow(NotFoundException::new);
        }
        if(Objects.equals(userActivate.getPin(), activationService.getPin(userActivate.getValue()))){
            assert entity != null;
            entity.setIsActivated(true);
            repository.saveAndFlush(entity);
            entityManager.refresh(entity);
            result.put("status", true);
            result.put("status_code", 200);
            activationService.deleteUser(entity.getId());
        }else {
            result.put("status", false);
            result.put("status_code", 400);
        }
        return result;
    }
}
