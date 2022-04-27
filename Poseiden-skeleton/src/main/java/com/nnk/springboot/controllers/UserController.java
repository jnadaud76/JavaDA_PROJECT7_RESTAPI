package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.IUserService;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute("users", userService.getUsers());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(@ModelAttribute("user") UserDto userDto) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            userService.addUser(userDto);
            model.addAttribute("users", userService.getUsers());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") UserDto userDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        userDto.setPassword(userDto.getPassword());
        userDto.setUsername(userDto.getUsername());
        userDto.setFullname(userDto.getFullname());
        userDto.setRole(userDto.getRole());
        userDto.setId(id);
        userService.addUser(userDto);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUserById(id);
        model.addAttribute("users", userService.getUsers());
        return "redirect:/user/list";
    }
}
