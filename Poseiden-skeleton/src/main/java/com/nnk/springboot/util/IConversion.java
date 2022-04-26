package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.dto.TradeDto;

public interface IConversion {

    BidListDto bidListToBidListDto(BidList bidList);

    CurvePointDto curvePointToCurvePointDto (CurvePoint curvePoint);

    RatingDto ratingToRatingDto(Rating rating);

    RuleNameDto ruleNameToRuleNameDto(RuleName ruleName);

    TradeDto tradeToTradeDto(Trade trade);
}
