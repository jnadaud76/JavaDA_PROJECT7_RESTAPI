package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.ICurveService;

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
public class CurveController {

    private final ICurveService curveService;

    public CurveController(ICurveService curveService) {
        this.curveService = curveService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
       model.addAttribute("curvePoints", curveService.getCurvePoints());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            curveService.addCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curveService.getCurvePoints());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curvePoint", curveService.getCurvePointById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id, @Valid  @ModelAttribute("curvePoint") CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePoint.setCurveId(curvePoint.getCurveId());
        curvePoint.setTerm(curvePoint.getTerm());
        curvePoint.setValue(curvePoint.getValue());
        curveService.addCurvePoint(curvePoint);
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        curveService.deleteCurvePointById(id);
        model.addAttribute("curvePoints", curveService.getCurvePoints());
        return "redirect:/curvePoint/list";
    }
}
