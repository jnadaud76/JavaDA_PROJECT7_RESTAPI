package com.nnk.springboot.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
@WithMockUser
class RatingControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestHome() throws Exception {
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    void TestAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "5"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/rating/update/20")));

    }

    @Test
    void TestUpdateRating() throws Exception {
        mockMvc.perform(post("/rating/update/2")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "5"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void TestUpdateRatingWithBadArguments() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    void TestDeleteRating() throws Exception {
        mockMvc.perform(get("/rating/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void TestDeleteRatingWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/rating/delete/20")));
    }
}
