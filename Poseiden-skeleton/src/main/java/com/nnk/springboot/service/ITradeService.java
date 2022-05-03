package com.nnk.springboot.service;

import com.nnk.springboot.dto.TradeDto;

import java.util.List;

public interface ITradeService {

    List<TradeDto> getTrades();

    Boolean addTrade(TradeDto tradeDto);

    TradeDto getTradeById(Integer id);

    Boolean deleteTradeById(Integer id);
}
