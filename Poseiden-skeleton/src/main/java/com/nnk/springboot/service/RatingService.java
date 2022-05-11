package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;

    private final IConversion conversion;

    public RatingService(RatingRepository ratingRepository, IConversion conversion) {
        this.ratingRepository = ratingRepository;
        this.conversion = conversion;
    }

    public List<RatingDto> getRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(conversion::ratingToRatingDto)
                .collect(Collectors.toList());
    }

    public Boolean addRating(RatingDto ratingDto) {
        ratingRepository.save(conversion.ratingDtoToRating(ratingDto));
        return true;
    }

    public RatingDto getRatingById(Integer id) {
        if (ratingRepository.existsById(id)) {
            return conversion.ratingToRatingDto(ratingRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid rating Id:" + id);
        }

    }

    public Boolean deleteRatingById(Integer id) {
        if (ratingRepository.existsById(id)) {
            ratingRepository.delete(ratingRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid rating Id:" + id);
        }
        return true;
    }
}
