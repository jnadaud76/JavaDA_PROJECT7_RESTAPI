package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class TradeService implements ITradeService {

    private final TradeRepository tradeRepository;

    private final IConversion conversion;

    public TradeService(TradeRepository tradeRepository, IConversion conversion) {
        this.tradeRepository = tradeRepository;
        this.conversion = conversion;
    }

    public List<TradeDto> getTrades() {
        List<Trade> trades = tradeRepository.findAll();
        return trades.stream()
                .map(conversion::tradeToTradeDto)
                .collect(Collectors.toList());
    }

    public Boolean addTrade(TradeDto tradeDto){
        tradeRepository.save(conversion.tradeDtoToTrade(tradeDto));
        return true;
    }

    public TradeDto getTradeById(Integer id){
        if(tradeRepository.existsById(id)){
            return conversion.tradeToTradeDto(tradeRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid trade Id:" + id);
        }

    }

    public Boolean deleteTradeById(Integer id){
        if(tradeRepository.existsById(id)){
            tradeRepository.delete(tradeRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid trade Id:" + id);
        }
        return true;
    }
}
