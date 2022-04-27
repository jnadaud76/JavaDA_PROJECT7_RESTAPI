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

public interface IConversion {

    BidListDto bidListToBidListDto(BidList bidList);

    BidList bidListDtoToBidList(BidListDto bidListDto);

    CurvePointDto curvePointToCurvePointDto (CurvePoint curvePoint);

    CurvePoint curvePointDtoToCurvePoint(CurvePointDto curvePointDto);

    RatingDto ratingToRatingDto(Rating rating);

    Rating ratingDtoToRating(RatingDto ratingDto);

    RuleNameDto ruleNameToRuleNameDto(RuleName ruleName);

    RuleName ruleNameDtoToRuleName(RuleNameDto ruleNameDto);

    TradeDto tradeToTradeDto(Trade trade);

    Trade tradeDtoToTrade(TradeDto tradeDto);

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
