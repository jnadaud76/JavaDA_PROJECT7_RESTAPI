package com.nnk.springboot.service;

import com.nnk.springboot.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    Boolean addUser(UserDto userDto);

    UserDto getUserById(Integer id);

    Boolean deleteUserById(Integer id);


}
