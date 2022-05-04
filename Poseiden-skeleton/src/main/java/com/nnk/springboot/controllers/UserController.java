package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.IUserService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API for user CRUD operations.")
@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Retrieving all user.")
    @GetMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.getUsers());
        LOGGER.info("Users successfully found - code : {}", HttpStatus.OK);
        return "user/list";
    }

    @ApiOperation(value = "Showing user creation form.")
    @GetMapping("/user/add")
    public String addUserForm(@ModelAttribute("user") UserDto userDto) {
        LOGGER.info("UserForm successfully found - code : {}", HttpStatus.OK);
        return "user/add";
    }

    @ApiOperation(value = "Adding one user after validation.")
    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            userService.addUser(userDto);
            model.addAttribute("users", userService.getUsers());
            LOGGER.info("User successfully saved - code : {}", HttpStatus.FOUND);
            return "redirect:/user/list";
        }
        LOGGER.error("User cannot be saved - code : {}", HttpStatus.OK);
        return "user/add";
    }

    @ApiOperation(value = "Showing user updating form.")
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        LOGGER.info("User UpdateForm successfully found - code : {}", HttpStatus.OK);
        return "user/update";
    }

    @ApiOperation(value = "Updating one user after validation.")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.error("User cannot be updated - code : {}", HttpStatus.OK);
            return "user/update";
        }

        userDto.setPassword(userDto.getPassword());
        userDto.setUsername(userDto.getUsername());
        userDto.setFullname(userDto.getFullname());
        userDto.setRole(userDto.getRole());
        userDto.setId(id);
        userService.addUser(userDto);
        model.addAttribute("users", userService.getUsers());
        LOGGER.info("User successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/user/list";
    }

    @ApiOperation(value = "Deleting one user.")
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("users", userService.getUsers());
        LOGGER.info("User successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/user/list";
    }
}
