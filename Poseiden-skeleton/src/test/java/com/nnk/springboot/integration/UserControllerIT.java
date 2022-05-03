package com.nnk.springboot.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.Application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "adminTest", authorities = {"ADMIN"})
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestHome() throws Exception {
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
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/user/delete/20")));
    }
}