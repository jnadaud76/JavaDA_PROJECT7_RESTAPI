package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.util.IConversion;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
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

    public Boolean addRuleName(RuleNameDto ruleNameDto){
        ruleNameRepository.save(conversion.ruleNameDtoToRuleName(ruleNameDto));
        return true;
    }

    public RuleNameDto getRuleNameById(Integer id){
        if (ruleNameRepository.existsById(id)){
            return conversion.ruleNameToRuleNameDto(ruleNameRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
    }

    public Boolean deleteRuleNameById(Integer id){
        if (ruleNameRepository.existsById(id)){
            ruleNameRepository.delete(ruleNameRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid ruleName Id:" + id);
        }
        return true;
    }
}
