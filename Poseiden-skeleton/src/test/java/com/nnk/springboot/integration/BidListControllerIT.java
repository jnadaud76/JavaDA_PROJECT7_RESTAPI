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
class BidListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestHome() throws Exception {
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    void TestAddBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "trois")
                        .param("type", "trois")
                        .param("bidQuantity", "300.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/bidList/validate")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", "300.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/bidList/update/20")));

    }

    @Test
    void TestUpdateBid() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .param("account", "quatre")
                        .param("type", "quatre")
                        .param("bidQuantity", "250.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void TestUpdateBidWithBadArguments() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", "300.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void TestDeleteBid() throws Exception {
        mockMvc.perform(get("/bidList/delete/2"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void TestDeleteBidWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/bidList/delete/20")));
    }
}
