package com.quoter.quoter.service;

import com.quoter.quoter.dto.UserDto;
import com.quoter.quoter.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}