package com.ndiaye.mongo.mongoapi.controller;

import com.ndiaye.mongo.mongoapi.dto.CreateUserDto;
import com.ndiaye.mongo.mongoapi.model.User;
import com.ndiaye.mongo.mongoapi.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        //Return all users through a user service
        return userService.getAllUsers();
    }

    @PostMapping
    public User creatUser(@RequestBody CreateUserDto createUserDto) {
        return userService.createUser(createUserDto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
}
