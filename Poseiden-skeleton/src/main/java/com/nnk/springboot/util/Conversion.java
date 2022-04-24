package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.dto.RatingDto;

import org.springframework.stereotype.Component;

@Component
public class Conversion implements IConversion{

    public BidListDto bidListToBidListDto(final BidList bidList) {
        BidListDto bidListFullDto = new BidListDto();
        bidListFullDto.setId(bidList.getId());
        bidListFullDto.setAccount(bidList.getAccount());
        bidListFullDto.setType(bidList.getType());
        bidListFullDto.setBidQuantity(bidList.getBidQuantity());
        return bidListFullDto;
    }

    public CurvePointDto curvePointToCurvePointDto(final CurvePoint curvePoint) {
        CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(curvePoint.getId());
        curvePointDto.setCurveId(curvePoint.getCurveId());
        curvePointDto.setTerm(curvePoint.getTerm());
        curvePointDto.setValue(curvePoint.getValue());
        return curvePointDto;
    }

    public RatingDto ratingToRatingDto(final Rating rating) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(rating.getId());
        ratingDto.setMoodysRating(rating.getMoodysRating());
        ratingDto.setSandPRating(rating.getSandPRating());
        ratingDto.setFitchRating(rating.getFitchRating());
        ratingDto.setOrderNumber(rating.getOrderNumber());
        return ratingDto;
    }
}
