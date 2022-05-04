package com.nnk.springboot.controllers;

import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.service.IRatingService;

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

@Api("API for rating CRUD operations.")
@Controller
public class RatingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    private final IRatingService ratingService;

    public RatingController(IRatingService ratingService) {
        this.ratingService = ratingService;
    }

    @ApiOperation(value = "Retrieving all rating.")
    @GetMapping("/rating/list")
    public String home(Model model)
    {
        model.addAttribute("ratings", ratingService.getRatings());
        LOGGER.info("Ratings successfully found - code : {}", HttpStatus.OK);
        return "rating/list";
    }

    @ApiOperation(value = "Showing rating creation form.")
    @GetMapping("/rating/add")
    public String addRatingForm(@ModelAttribute("rating") RatingDto ratingDto) {
        LOGGER.info("RatingForm successfully found - code : {}", HttpStatus.OK);
        return "rating/add";
    }

    @ApiOperation(value = "Adding one rating after validation.")
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") RatingDto ratingDto, BindingResult result, Model model) {
            if (!result.hasErrors()) {
            ratingService.addRating(ratingDto);
            model.addAttribute("ratings", ratingService.getRatings());
            LOGGER.info("Rating successfully saved - code : {}", HttpStatus.FOUND);
            return "redirect:/rating/list";
        }
        LOGGER.error("Rating cannot be saved - code : {}", HttpStatus.OK);
        return "rating/add";
    }

    @ApiOperation(value = "Showing rating updating form.")
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("rating", ratingService.getRatingById(id));
        LOGGER.info("Rating UpdateForm successfully found - code : {}", HttpStatus.OK);
        return "rating/update";
    }

    @ApiOperation(value = "Updating one rating after validation.")
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") RatingDto ratingDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            LOGGER.error("Rating cannot be updated - code : {}", HttpStatus.OK);
            return "rating/update";
        }
        ratingDto.setId(ratingDto.getId());
        ratingDto.setMoodysRating(ratingDto.getMoodysRating());
        ratingDto.setSandPRating(ratingDto.getSandPRating());
        ratingDto.setFitchRating(ratingDto.getFitchRating());
        ratingDto.setOrderNumber(ratingDto.getOrderNumber());
        ratingService.addRating(ratingDto);
        model.addAttribute("ratings", ratingService.getRatings());
        LOGGER.info("Rating successfully updated - code : {}", HttpStatus.FOUND);
        return "redirect:/rating/list";
    }

    @ApiOperation(value = "Deleting one rating.")
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRatingById(id);
        model.addAttribute("ratings", ratingService.getRatings());
        LOGGER.info("Rating successfully delete - code : {}", HttpStatus.FOUND);
        return "redirect:/rating/list";
    }
}
