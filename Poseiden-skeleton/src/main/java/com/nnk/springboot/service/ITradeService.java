package com.nnk.springboot.service;

import com.nnk.springboot.dto.TradeDto;

import java.util.List;

public interface ITradeService {

    List<TradeDto> getTrades();

    void addTrade(TradeDto tradeDto);

    TradeDto getTradeById(Integer id);

    void deleteTradeById(Integer id);
}
