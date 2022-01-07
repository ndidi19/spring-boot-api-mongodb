package com.ndiaye.mongo.mongoapi.service;

import com.ndiaye.mongo.mongoapi.dto.CreateUserDto;
import com.ndiaye.mongo.mongoapi.model.User;

import java.util.List;

public interface IUserService {

    public List<User> getAllUsers();

    User createUser(CreateUserDto createUserDto);

    User getUserById(String id);
}
