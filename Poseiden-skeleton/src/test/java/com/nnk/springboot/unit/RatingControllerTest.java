package com.nnk.springboot.unit;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.service.IRatingService;
import com.nnk.springboot.service.MyUserDetailsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

@WithMockUser
@WebMvcTest(controllers = RatingController.class)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private IRatingService ratingService;

    @Test
    void TestHome() throws Exception {
        List<RatingDto> ratingDtos = new ArrayList<>();

        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(1);
        ratingDto.setMoodysRating("un");
        ratingDto.setSandPRating("un");
        ratingDto.setFitchRating("un");
        ratingDto.setOrderNumber(1);

        RatingDto ratingDto2 = new RatingDto();
        ratingDto2.setId(2);
        ratingDto2.setMoodysRating("deux");
        ratingDto2.setSandPRating("deux");
        ratingDto2.setFitchRating("deux");
        ratingDto2.setOrderNumber(2);

        ratingDtos.add(ratingDto);
        ratingDtos.add(ratingDto2);

        when(ratingService.getRatings()).thenReturn(ratingDtos);
        mockMvc.perform(get("/rating/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("ratings"));
    }

    @Test
    void TestAddRatingForm() throws Exception {
        mockMvc.perform(get("/rating/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        List<RatingDto> ratingDtos = new ArrayList<>();

        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(5);
        ratingDto.setMoodysRating("cinq");
        ratingDto.setSandPRating("cinq");
        ratingDto.setFitchRating("cinq");
        ratingDto.setOrderNumber(5);

        ratingDtos.add(ratingDto);

        when(ratingService.getRatings()).thenReturn(ratingDtos);

        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "5"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/rating/validate")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(1);
        ratingDto.setMoodysRating("un");
        ratingDto.setSandPRating("un");
        ratingDto.setFitchRating("un");
        ratingDto.setOrderNumber(1);
        when(ratingService.getRatingById(1)).thenReturn(ratingDto);
        mockMvc.perform(get("/rating/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rating"))
                .andExpect(view().name("rating/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/rating/update/20")));

    }

    @Test
    void TestUpdateRating() throws Exception {
        mockMvc.perform(post("/rating/update/2")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "5"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void TestUpdateRatingWithBadArguments() throws Exception {
        mockMvc.perform(post("/rating/update/1")
                        .param("moodysRating", "cinq")
                        .param("sandPRating", "cinq")
                        .param("fitchRating", "cinq")
                        .param("orderNumber", "cinq"))
                .andExpect(status().isOk())
                .andExpect(view().name("rating/update"));
    }

    @Test
    void TestDeleteRating() throws Exception {
        mockMvc.perform(get("/rating/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/rating/list"));
    }

    @Test
    void TestDeleteRatingWithBadId() throws Exception {
        when(ratingService.deleteRatingById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/rating/delete/20")));
    }
}

