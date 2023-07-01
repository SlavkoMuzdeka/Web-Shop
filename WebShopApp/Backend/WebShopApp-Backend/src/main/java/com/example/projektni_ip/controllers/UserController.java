package com.example.projektni_ip.controllers;

import com.example.projektni_ip.entities.UserEntity;
import com.example.projektni_ip.entities.dto.User;
import com.example.projektni_ip.entities.dto.UserActivate;
import com.example.projektni_ip.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, Object> authenticate(@RequestBody UserEntity userEntity) {
        return userService.authenticate(userEntity.getUserName(), userEntity.getPassword());
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> updateUser(@PathVariable Integer userId, @RequestBody User user) {
        return userService.update(userId, user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> insert(@RequestBody User user) {
        return userService.insert(user);
    }


    @PutMapping("/activate")
    public Map<String, Object> activateUser(@RequestBody UserActivate userActivate) {
        System.out.println(userActivate);
        return userService.activateUser(userActivate);
    }

}
