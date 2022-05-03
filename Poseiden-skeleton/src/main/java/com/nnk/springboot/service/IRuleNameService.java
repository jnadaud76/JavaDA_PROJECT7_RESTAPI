package com.nnk.springboot.service;

import com.nnk.springboot.dto.RuleNameDto;

import java.util.List;

public interface IRuleNameService {

    List<RuleNameDto> getRuleNames();

    Boolean addRuleName(RuleNameDto ruleNameDto);

    RuleNameDto getRuleNameById(Integer id);

    Boolean deleteRuleNameById(Integer id);
}
