package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.service.IRuleNameService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RuleNameController {

    private final IRuleNameService ruleNameService;

    public RuleNameController(IRuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(@ModelAttribute("ruleName") RuleNameDto ruleNameDto) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleNameDto ruleNameDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleNameDto);
            model.addAttribute("ruleNames", ruleNameService.getRuleNames());
            return "redirect:/ruleName/list";
        }
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleNameDto ruleNameDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameDto.setId(ruleNameDto.getId());
        ruleNameDto.setName(ruleNameDto.getName());
        ruleNameDto.setDescription(ruleNameDto.getDescription());
        ruleNameDto.setJson(ruleNameDto.getJson());
        ruleNameDto.setTemplate(ruleNameDto.getTemplate());
        ruleNameDto.setSqlPart(ruleNameDto.getSqlPart());
        ruleNameDto.setSqlStr(ruleNameDto.getSqlStr());
        ruleNameService.addRuleName(ruleNameDto);
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleNameById(id);
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        return "redirect:/ruleName/list";
    }
}
