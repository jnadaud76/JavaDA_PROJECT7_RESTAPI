package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RuleNameServiceTest {

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Autowired
    private RuleNameService ruleNameService;

    @Test
    void TestGetRuleNames() throws Exception {
        //Given
        List<RuleName> ruleNames = new ArrayList<>();

        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("un");
        ruleName.setDescription("un");
        ruleName.setJson("un");
        ruleName.setTemplate("un");
        ruleName.setSqlStr("un");
        ruleName.setSqlPart("un");

        RuleName ruleName2 = new RuleName();
        ruleName2.setId(2);
        ruleName2.setName("deux");
        ruleName2.setDescription("deux");
        ruleName2.setJson("deux");
        ruleName2.setTemplate("deux");
        ruleName2.setSqlStr("deux");
        ruleName2.setSqlPart("deux");

        ruleNames.add(ruleName);
        ruleNames.add(ruleName2);

        //When
        when(ruleNameRepository.findAll()).thenReturn(ruleNames);

        //Then
        assertFalse(ruleNameService.getRuleNames().isEmpty());
        assertEquals(2, ruleNameService.getRuleNames().size());
    }

    @Test
    void TestGetRuleNameById() throws Exception {
        //Given
        RuleName ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("un");
        ruleName.setDescription("un");
        ruleName.setJson("un");
        ruleName.setTemplate("un");
        ruleName.setSqlStr("un");
        ruleName.setSqlPart("un");

        //When
        when(ruleNameRepository.existsById(1)).thenReturn(true);
        when(ruleNameRepository.getById(1)).thenReturn(ruleName);

        //Then
        assertEquals(1, ruleNameService.getRuleNameById(1).getId());
        assertEquals("un", ruleNameService.getRuleNameById(1).getName());
        assertEquals("un", ruleNameService.getRuleNameById(1).getDescription());
        assertEquals("un", ruleNameService.getRuleNameById(1).getJson());
        assertEquals("un", ruleNameService.getRuleNameById(1).getTemplate());
        assertEquals("un", ruleNameService.getRuleNameById(1).getSqlStr());
        assertEquals("un", ruleNameService.getRuleNameById(1).getSqlPart());
    }

    @Test
    void TestGetRuleNameByIdWithBadId() throws Exception {
        //Given
        when(ruleNameRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> ruleNameService.getRuleNameById(20));
    }

    @Test
    void TestAddBidListWithNull() throws Exception {
        //Given
        when(ruleNameRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> ruleNameService.addRuleName(null));
    }

    @Test
    void TestDeleteBidByIdWithBadId() throws Exception {
        //Given
        when(ruleNameRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> ruleNameService.deleteRuleNameById(20));
    }

}
