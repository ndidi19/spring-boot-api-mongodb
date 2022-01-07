package com.ndiaye.mongo.mongoapi.service.impl;

import com.ndiaye.mongo.mongoapi.dto.CreateUserDto;
import com.ndiaye.mongo.mongoapi.model.Address;
import com.ndiaye.mongo.mongoapi.model.User;
import com.ndiaye.mongo.mongoapi.repository.UserRepository;
import com.ndiaye.mongo.mongoapi.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        Address address = new Address(
                createUserDto.getStreet(),
                createUserDto.getZipCode(),
                createUserDto.getCity(),
                createUserDto.getCountry()
        );
        User u = new User();
        u.setFirstname(createUserDto.getFirstname());
        u.setLastname(createUserDto.getLastname());
        u.setUsername(createUserDto.getUsername());
        u.setEmail(createUserDto.getEmail());
        u.setGender(createUserDto.getGender());
        u.setAddress(address);
        return userRepository.insert(u);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found for id : " + id));
    }
}
