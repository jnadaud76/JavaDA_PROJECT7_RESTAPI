package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.dto.UserDto;
import com.nnk.springboot.service.IUserService;
import com.nnk.springboot.service.MyUserDetailsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

@WithMockUser(username = "adminTest", authorities = {"ADMIN"})
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private IUserService userService;

    @Test
    void TestHome() throws Exception {
        List<UserDto> userDtos = new ArrayList<>();

        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("user");
        userDto.setPassword("Le123456789*");
        userDto.setFullname("User");
        userDto.setRole("USER");

        UserDto userDto2 = new UserDto();
        userDto2.setId(2);
        userDto2.setUsername("admin");
        userDto2.setPassword("Li987654321*");
        userDto2.setFullname("Administrator");
        userDto2.setRole("ADMIN");

        userDtos.add(userDto);
        userDtos.add(userDto2);

        when(userService.getUsers()).thenReturn(userDtos);

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void TestAddUserForm() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        List<UserDto> userDtos = new ArrayList<>();

        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("test");
        userDto.setPassword("Le123456789*");
        userDto.setFullname("test");
        userDto.setRole("USER");

        userDtos.add(userDto);

        when(userService.getUsers()).thenReturn(userDtos);

        mockMvc.perform(post("/user/validate")
                        .param("username", "test")
                        .param("password", "Le123456789*")
                        .param("fullname", "test")
                        .param("role", "USER"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/user/validate")
                        .param("username", "test")
                        .param("password", "123")
                        .param("fullname", "test")
                        .param("role", "USER"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setUsername("user");
        userDto.setPassword("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa");
        userDto.setFullname("User");
        userDto.setRole("USER");
        when(userService.getUserById(1)).thenReturn(userDto);
        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/user/update/20")));

    }

    @Test
    void TestUpdateUser() throws Exception {
        mockMvc.perform(post("/user/update/2")
                        .param("username", "test5")
                        .param("password", "Re123456789*")
                        .param("fullname", "test5")
                        .param("role", "USER"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void TestUpdateUserWithBadArguments() throws Exception {
        mockMvc.perform(post("/user/update/1")
                        .param("username", "")
                        .param("password", "")
                        .param("fullname", "")
                        .param("role", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    void TestDeleteUser() throws Exception {
        mockMvc.perform(get("/user/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void TestDeleteUserWithBadId() throws Exception {
        when(userService.deleteUserById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/user/delete/20")));
    }
}
