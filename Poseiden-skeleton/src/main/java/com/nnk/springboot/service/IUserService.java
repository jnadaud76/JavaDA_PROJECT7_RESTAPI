package com.nnk.springboot.service;

import com.nnk.springboot.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    void addUser(UserDto userDto);

    UserDto getUserById(Integer id);

    void deleteUserById(Integer id);


}
