package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.util.IConversion;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleNameService implements IRuleNameService {

    private final RuleNameRepository ruleNameRepository;

    private final IConversion conversion;

    public RuleNameService(RuleNameRepository ruleNameRepository, IConversion conversion) {
        this.ruleNameRepository = ruleNameRepository;
        this.conversion = conversion;
    }

    public List<RuleNameDto> getRuleNames() {
        List<RuleName> ruleNames = ruleNameRepository.findAll();
        return ruleNames.stream()
                .map(conversion::ruleNameToRuleNameDto)
                .collect(Collectors.toList());
    }

    public void addRuleName(RuleName ruleName){
        ruleNameRepository.save(ruleName);
    }

    public RuleNameDto getRuleNameById(Integer id){
        if (ruleNameRepository.existsById(id)){
            return conversion.ruleNameToRuleNameDto(ruleNameRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
    }

    public void deleteRuleNameById(Integer id){
        if (ruleNameRepository.existsById(id)){
            ruleNameRepository.delete(ruleNameRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
    }
}