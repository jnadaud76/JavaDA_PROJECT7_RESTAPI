package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.service.IRatingService;

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
public class RatingController {

    private final IRatingService ratingService;

    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getRatings());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(@ModelAttribute("rating") RatingDto ratingDto) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") RatingDto ratingDto, BindingResult result, Model model) {
            if (!result.hasErrors()) {
            ratingService.addRating(ratingDto);
            model.addAttribute("ratings", ratingService.getRatings());
            return "redirect:/rating/list";
        }

        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("rating", ratingService.getRatingById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") RatingDto ratingDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingDto.setId(ratingDto.getId());
        ratingDto.setMoodysRating(ratingDto.getMoodysRating());
        ratingDto.setSandPRating(ratingDto.getSandPRating());
        ratingDto.setFitchRating(ratingDto.getFitchRating());
        ratingDto.setOrderNumber(ratingDto.getOrderNumber());
        ratingService.addRating(ratingDto);
        model.addAttribute("ratings", ratingService.getRatings());
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRatingById(id);
        model.addAttribute("ratings", ratingService.getRatings());
        return "redirect:/rating/list";
    }
}
