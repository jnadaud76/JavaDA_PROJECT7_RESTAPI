package com.nnk.springboot.service;

import com.nnk.springboot.dto.RatingDto;

import java.util.List;

public interface IRatingService {

    List<RatingDto> getRatings();

    Boolean addRating(RatingDto ratingDto);

    RatingDto getRatingById(Integer id);

    Boolean deleteRatingById(Integer id);
}
