package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.dto.UserDto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Conversion implements IConversion {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public BidListDto bidListToBidListDto(final BidList bidList) {
        BidListDto bidListDto = new BidListDto();
        bidListDto.setId(bidList.getId());
        bidListDto.setAccount(bidList.getAccount());
        bidListDto.setType(bidList.getType());
        bidListDto.setBidQuantity(bidList.getBidQuantity());
        return bidListDto;
    }

    public BidList bidListDtoToBidList(final BidListDto bidListDto) {
        BidList bidList = new BidList();
        bidList.setId(bidListDto.getId());
        bidList.setAccount(bidListDto.getAccount());
        bidList.setType(bidListDto.getType());
        bidList.setBidQuantity(bidListDto.getBidQuantity());
        return bidList;
    }


    public CurvePointDto curvePointToCurvePointDto(final CurvePoint curvePoint) {
        CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(curvePoint.getId());
        curvePointDto.setCurveId(curvePoint.getCurveId());
        curvePointDto.setTerm(curvePoint.getTerm());
        curvePointDto.setValue(curvePoint.getValue());
        return curvePointDto;
    }

    public CurvePoint curvePointDtoToCurvePoint(final CurvePointDto curvePointDto) {
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(curvePointDto.getId());
        curvePoint.setCurveId(curvePointDto.getCurveId());
        curvePoint.setTerm(curvePointDto.getTerm());
        curvePoint.setValue(curvePointDto.getValue());
        return curvePoint;
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

    public Rating ratingDtoToRating(final RatingDto ratingDto) {
        Rating rating = new Rating();
        rating.setId(ratingDto.getId());
        rating.setMoodysRating(ratingDto.getMoodysRating());
        rating.setSandPRating(ratingDto.getSandPRating());
        rating.setFitchRating(ratingDto.getFitchRating());
        rating.setOrderNumber(ratingDto.getOrderNumber());
        return rating;
    }

    public RuleNameDto ruleNameToRuleNameDto(final RuleName ruleName) {
        RuleNameDto ruleNameDto = new RuleNameDto();
        ruleNameDto.setId(ruleName.getId());
        ruleNameDto.setName(ruleName.getName());
        ruleNameDto.setDescription(ruleName.getDescription());
        ruleNameDto.setJson(ruleName.getJson());
        ruleNameDto.setTemplate(ruleName.getTemplate());
        ruleNameDto.setSqlStr(ruleName.getSqlStr());
        ruleNameDto.setSqlPart(ruleName.getSqlPart());
        return ruleNameDto;
    }

    public RuleName ruleNameDtoToRuleName(final RuleNameDto ruleNameDto) {
        RuleName ruleName = new RuleName();
        ruleName.setId(ruleNameDto.getId());
        ruleName.setName(ruleNameDto.getName());
        ruleName.setDescription(ruleNameDto.getDescription());
        ruleName.setJson(ruleNameDto.getJson());
        ruleName.setTemplate(ruleNameDto.getTemplate());
        ruleName.setSqlStr(ruleNameDto.getSqlStr());
        ruleName.setSqlPart(ruleNameDto.getSqlPart());
        return ruleName;
    }

    public TradeDto tradeToTradeDto(final Trade trade) {
        TradeDto tradeDto = new TradeDto();
        tradeDto.setId(trade.getId());
        tradeDto.setAccount(trade.getAccount());
        tradeDto.setType(trade.getType());
        tradeDto.setBuyQuantity(trade.getBuyQuantity());
        return tradeDto;
    }

    public Trade tradeDtoToTrade(final TradeDto tradeDto) {
        Trade trade = new Trade();
        trade.setId(tradeDto.getId());
        trade.setAccount(tradeDto.getAccount());
        trade.setType(tradeDto.getType());
        trade.setBuyQuantity(tradeDto.getBuyQuantity());
        return trade;
    }

    public UserDto userToUserDto(final User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFullname(user.getFullname());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public User userDtoToUser(final UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder().encode(userDto.getPassword()));
        user.setFullname(userDto.getFullname());
        user.setRole(userDto.getRole());
        return user;
    }
}
