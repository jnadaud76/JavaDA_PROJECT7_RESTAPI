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
class RuleNameControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestHome() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleNames"));
    }

    @Test
    void TestAddBidForm() throws Exception {
        mockMvc.perform(get("/ruleName/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "cinq")
                        .param("description", "cinq")
                        .param("json", "cinq")
                        .param("template", "cinq")
                        .param("sqlStr", "cinq")
                        .param("sqlPart", "cinq"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/ruleName/validate")
                        .param("name",
                                "123456789123456789123456789123456789"+
                                "12345678912345678912346567891234567891323456789"+
                                "12345678912345667891234567891323456789123456789")
                        .param("description", "cinq")
                        .param("json", "cinq")
                        .param("template", "cinq")
                        .param("sqlStr", "cinq")
                        .param("sqlPart", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ruleName"))
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/ruleName/update/20")));

    }

    @Test
    void TestUpdateBid() throws Exception {
        mockMvc.perform(post("/ruleName/update/2")
                        .param("name", "cinq")
                        .param("description", "cinq")
                        .param("json", "cinq")
                        .param("template", "cinq")
                        .param("sqlStr", "cinq")
                        .param("sqlPart", "cinq"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void TestUpdateBidWithBadArguments() throws Exception {
        mockMvc.perform(post("/ruleName/update/1")
                        .param("name",
                                "123456789123456789123456789123456789"+
                                        "12345678912345678912346567891234567891323456789"+
                                        "12345678912345667891234567891323456789123456789")
                        .param("description", "cinq")
                        .param("json", "cinq")
                        .param("template", "cinq")
                        .param("sqlStr", "cinq")
                        .param("sqlPart", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void TestDeleteBid() throws Exception {
        mockMvc.perform(get("/ruleName/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void TestDeleteBidWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/ruleName/delete/20")));
    }
}
