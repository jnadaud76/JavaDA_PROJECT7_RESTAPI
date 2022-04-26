package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;

import java.util.List;

public interface ITradeService {

    List<TradeDto> getTrades();

    void addTrade(Trade trade);

    TradeDto getTradeById(Integer id);

    void deleteTradeById(Integer id);
}
