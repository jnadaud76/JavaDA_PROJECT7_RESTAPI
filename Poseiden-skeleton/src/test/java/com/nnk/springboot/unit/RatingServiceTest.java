package com.nnk.springboot.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class RatingServiceTest {

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Test
    void TestGetRatings() throws Exception {
        //Given
        List<Rating> ratings = new ArrayList<>();

        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("un");
        rating.setSandPRating("un");
        rating.setFitchRating("un");
        rating.setOrderNumber(1);

        Rating rating2 = new Rating();
        rating2.setId(2);
        rating2.setMoodysRating("deux");
        rating2.setSandPRating("deux");
        rating2.setFitchRating("deux");
        rating2.setOrderNumber(2);

        ratings.add(rating);
        ratings.add(rating2);

        //When
        when(ratingRepository.findAll()).thenReturn(ratings);

        //Then
        assertFalse(ratingService.getRatings().isEmpty());
        assertEquals(2, ratingService.getRatings().size());

    }

    @Test
    void TestGetRatingById() throws Exception {
        //Given
        Rating rating = new Rating();
        rating.setId(1);
        rating.setMoodysRating("un");
        rating.setSandPRating("un");
        rating.setFitchRating("un");
        rating.setOrderNumber(1);

        //When
        when(ratingRepository.existsById(1)).thenReturn(true);
        when(ratingRepository.getById(1)).thenReturn(rating);

        //Then
        Assertions.assertEquals(1, ratingService.getRatingById(1).getId());
        Assertions.assertEquals("un", ratingService.getRatingById(1).getMoodysRating());
        Assertions.assertEquals("un", ratingService.getRatingById(1).getSandPRating());
        Assertions.assertEquals("un", ratingService.getRatingById(1).getFitchRating());
        Assertions.assertEquals(1, ratingService.getRatingById(1).getOrderNumber());

    }

    @Test
    void TestGetRatingByIdWithBadId() throws Exception {

        //Given
        when(ratingRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> ratingService.getRatingById(20));
    }

    @Test
    void TestAddRatingWithNull() throws Exception {
        //Given
        when(ratingRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> ratingService.addRating(null));
    }

    @Test
    void TestDeleteRatingByIdWithBadId() throws Exception {
        //Given
        when(ratingRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> ratingService.deleteRatingById(20));
    }

}
