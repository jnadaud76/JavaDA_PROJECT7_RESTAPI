package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.service.IRuleNameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("API for rulename CRUD operations.")
@Controller
public class RuleNameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleNameController.class);

    private final IRuleNameService ruleNameService;

    public RuleNameController(IRuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @ApiOperation(value = "Retrieving all rulename.")
    @GetMapping("/ruleName/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        LOGGER.info("RuleNames successfully found - code : {}", HttpStatus.OK);
        return "ruleName/list";
    }

    @ApiOperation(value = "Showing rulename creation form.")
    @GetMapping("/ruleName/add")
    public String addRuleForm(@ModelAttribute("ruleName") RuleNameDto ruleNameDto) {
        LOGGER.info("RuleNameForm successfully found - code : {}", HttpStatus.OK);
        return "ruleName/add";
    }

    @ApiOperation(value = "Adding one rulename after validation.")
    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleNameDto ruleNameDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            ruleNameService.addRuleName(ruleNameDto);
            model.addAttribute("ruleNames", ruleNameService.getRuleNames());
            LOGGER.info("RuleName successfully saved - code : {}", HttpStatus.FOUND);
            return "redirect:/ruleName/list";
        }
        LOGGER.error("RuleName cannot be saved - code : {}", HttpStatus.OK);
        return "ruleName/add";
    }


    @ApiOperation(value = "Showing rulename updating form.")
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        LOGGER.info("RuleName UpdateForm successfully found - code : {}", HttpStatus.OK);
        model.addAttribute("ruleName", ruleNameService.getRuleNameById(id));
        return "ruleName/update";
    }

    @ApiOperation(value = "Updating one rulename after validation.")
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleNameDto ruleNameDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.error("RuleName cannot be updated - code : {}", HttpStatus.OK);
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
        LOGGER.info("RuleName successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/ruleName/list";
    }

    @ApiOperation(value = "Deleting one rulename.")
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRuleNameById(id);
        model.addAttribute("ruleNames", ruleNameService.getRuleNames());
        LOGGER.info("RuleName successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/ruleName/list";
    }
}
