package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void TestGetUsers() throws Exception {
        //Given
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa");
        user.setFullname("User");
        user.setRole("USER");

        User admin = new User();
        admin.setId(2);
        admin.setUsername("admin");
        admin.setPassword("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa");
        admin.setFullname("Administrator");
        admin.setRole("ADMIN");

        users.add(user);
        users.add(admin);

        //When
        when(userRepository.findAll()).thenReturn(users);

        //Then
        assertFalse(userService.getUsers().isEmpty());
        assertEquals(2, userService.getUsers().size());
    }

    @Test
    void TestGetUserById() throws Exception {
        //Given
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa");
        user.setFullname("User");
        user.setRole("USER");

        //When
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.getById(1)).thenReturn(user);

        //Then
        assertEquals(1, userService.getUserById(1).getId());
        assertEquals("user", userService.getUserById(1).getUsername());
        assertEquals("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa", userService.getUserById(1).getPassword());
        assertEquals("User", userService.getUserById(1).getFullname());
        assertEquals("USER", userService.getUserById(1).getRole());
    }

    @Test
    void TestGetUserByIdWithBadId() throws Exception {

        //Given
        when(userRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> userService.getUserById(20));
    }

    @Test
    void TestAddUserWithNull() throws Exception {
        //Given
        when(userRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> userService.addUser(null));
    }

    @Test
    void TestDeleteUserByIdWithBadId() throws Exception {
        //Given
        when(userRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> userService.deleteUserById(20));
    }
}
