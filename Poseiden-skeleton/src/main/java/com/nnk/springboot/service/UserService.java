package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;

import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final IConversion conversion;

    public UserService(UserRepository userRepository, IConversion conversion) {
        this.userRepository = userRepository;
        this.conversion = conversion;
    }

   public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(conversion::userToUserDto)
                .collect(Collectors.toList());
    }

    public Boolean addUser(UserDto userDto){
        userRepository.save(conversion.userDtoToUser(userDto));
        return true;
        }

    public UserDto getUserById(Integer id){
        if(userRepository.existsById(id)){
            return conversion.userToUserDto(userRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }

    }

    public Boolean deleteUserById(Integer id){
        if(userRepository.existsById(id)){
            userRepository.delete(userRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid user Id:" + id);
        }
        return true;
    }
}
