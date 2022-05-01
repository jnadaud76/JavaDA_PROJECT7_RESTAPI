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
class CurveControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void TestHome() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoints"));
    }

    @Test
    void TestAddCurveForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "5")
                        .param("term", "90.0")
                        .param("value", "100.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "")
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/curvePoint/update/20")));

    }

    @Test
    void TestUpdateCurve() throws Exception {
        mockMvc.perform(post("/curvePoint/update/2")
                        .param("curveId", "4")
                        .param("term", "110.0")
                        .param("value", "110.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void TestUpdateCurveWithBadArguments() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "")
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void TestDeleteCurve() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void TestDeleteCurveWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/curvePoint/delete/20")));
    }

}
