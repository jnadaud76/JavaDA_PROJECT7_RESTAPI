package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.service.ICurveService;

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

@Api("API for curvepoint CRUD operations.")
@Controller
public class CurveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurveController.class);

    private final ICurveService curveService;

    public CurveController(ICurveService curveService) {
        this.curveService = curveService;
    }

    @ApiOperation(value = "Retrieving all curvepoint.")
    @GetMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        LOGGER.info("CurvePoints successfully found - code : {}", HttpStatus.OK);
        return "curvePoint/list";
    }

    @ApiOperation(value = "Showing curvepoint creation form.")
    @GetMapping("/curvePoint/add")
    public String addCurveForm(@ModelAttribute("curvePoint") CurvePointDto curvePointDto) {
        LOGGER.info("CurvePointForm successfully found - code : {}", HttpStatus.OK);
        return "curvePoint/add";
    }

    @ApiOperation(value = "Adding one curvepoint after validation.")
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointDto curvePointDto, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curveService.addCurvePoint(curvePointDto);
            model.addAttribute("curvePoints", curveService.getCurvePoints());
            LOGGER.info("CurvePoint successfully saved - code : {}", HttpStatus.FOUND);
            return "redirect:/curvePoint/list";
        }
        LOGGER.error("CurvePoint cannot be saved - code : {}", HttpStatus.OK);
        return "curvePoint/add";
    }

    @ApiOperation(value = "Showing curvepoint updating form.")
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curvePoint", curveService.getCurvePointById(id));
        LOGGER.info("CurvePoint UpdateForm successfully found - code : {}", HttpStatus.OK);
        return "curvePoint/update";
    }

    @ApiOperation(value = "Updating one curvepoint after validation.")
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid @ModelAttribute("curvePoint") CurvePointDto curvePointDto,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.error("CurvePoint cannot be updated - code : {}", HttpStatus.OK);
            return "curvePoint/update";
        }
        curvePointDto.setId(id);
        curvePointDto.setCurveId(curvePointDto.getCurveId());
        curvePointDto.setTerm(curvePointDto.getTerm());
        curvePointDto.setValue(curvePointDto.getValue());
        curveService.addCurvePoint(curvePointDto);
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        LOGGER.info("CurvePoint successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/curvePoint/list";
    }

    @ApiOperation(value = "Deleting one curvepoint.")
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        curveService.deleteCurvePointById(id);
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        LOGGER.info("CurvePoint successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/curvePoint/list";
    }
}
