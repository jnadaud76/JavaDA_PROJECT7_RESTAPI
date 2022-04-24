package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;

import java.util.List;

public interface IRatingService {

    List<RatingDto> getRatings();

    void addRating(Rating rating);

    RatingDto getRatingById(Integer id);

    void deleteRatingById(Integer id);
}
