package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.service.IRuleNameService;
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

@WithMockUser
@WebMvcTest(controllers = RuleNameController.class)
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private IRuleNameService ruleNameService;

    @Test
    void TestHome() throws Exception {
        List<RuleNameDto> ruleNameDtos = new ArrayList<>();

        RuleNameDto ruleNameDto = new RuleNameDto();
        ruleNameDto.setId(1);
        ruleNameDto.setName("un");
        ruleNameDto.setDescription("un");
        ruleNameDto.setJson("un");
        ruleNameDto.setTemplate("un");
        ruleNameDto.setSqlStr("un");
        ruleNameDto.setSqlPart("un");

        RuleNameDto ruleNameDto2 = new RuleNameDto();
        ruleNameDto2.setId(2);
        ruleNameDto2.setName("deux");
        ruleNameDto2.setDescription("deux");
        ruleNameDto2.setJson("deux");
        ruleNameDto2.setTemplate("deux");
        ruleNameDto2.setSqlStr("deux");
        ruleNameDto2.setSqlPart("deux");

        ruleNameDtos.add(ruleNameDto);
        ruleNameDtos.add(ruleNameDto2);

        when(ruleNameService.getRuleNames()).thenReturn(ruleNameDtos);
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
        List<RuleNameDto> ruleNameDtos = new ArrayList<>();

        RuleNameDto ruleNameDto = new RuleNameDto();
        ruleNameDto.setId(5);
        ruleNameDto.setName("cinq");
        ruleNameDto.setDescription("cinq");
        ruleNameDto.setJson("cinq");
        ruleNameDto.setTemplate("cinq");
        ruleNameDto.setSqlStr("cinq");
        ruleNameDto.setSqlPart("cinq");
        ruleNameDtos.add(ruleNameDto);

        when(ruleNameService.getRuleNames()).thenReturn(ruleNameDtos);
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
        RuleNameDto ruleNameDto = new RuleNameDto();
        ruleNameDto.setId(1);
        ruleNameDto.setName("un");
        ruleNameDto.setDescription("un");
        ruleNameDto.setJson("un");
        ruleNameDto.setTemplate("un");
        ruleNameDto.setSqlStr("un");
        ruleNameDto.setSqlPart("un");
        when(ruleNameService.getRuleNameById(1)).thenReturn(ruleNameDto);
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
        when(ruleNameService.deleteRuleNameById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/ruleName/delete/20")));
    }
}

