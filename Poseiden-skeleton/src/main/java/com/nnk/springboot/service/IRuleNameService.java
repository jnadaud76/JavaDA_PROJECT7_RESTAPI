package com.nnk.springboot.service;

import com.nnk.springboot.dto.RuleNameDto;

import java.util.List;

public interface IRuleNameService {

    List<RuleNameDto> getRuleNames();

    void addRuleName(RuleNameDto ruleNameDto);

    RuleNameDto getRuleNameById(Integer id);

    void deleteRuleNameById(Integer id);
}
