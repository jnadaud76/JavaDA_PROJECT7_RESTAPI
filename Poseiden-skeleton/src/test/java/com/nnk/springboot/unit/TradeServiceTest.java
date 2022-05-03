package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TradeServiceTest {

    @MockBean
    private TradeRepository tradeRepository;

    @Autowired
    private TradeService tradeService;

    @Test
    void TestGetTrades() throws Exception {
        //Given
        List<Trade> trades = new ArrayList<>();

        Trade trade = new Trade();
        trade.setId(1);
        trade.setAccount("un");
        trade.setType("un");
        trade.setBuyQuantity(10.0);

        Trade trade2 = new Trade();
        trade2.setId(2);
        trade2.setAccount("deux");
        trade2.setType("deux");
        trade2.setBuyQuantity(20.0);

        trades.add(trade);
        trades.add(trade2);

        //When
        when(tradeRepository.findAll()).thenReturn(trades);

        //Then
        assertFalse(tradeService.getTrades().isEmpty());
        assertEquals(2, tradeService.getTrades().size());
    }

    @Test
    void TestGetTradeById() throws Exception {
        //Given
        Trade trade = new Trade();
        trade.setId(1);
        trade.setAccount("un");
        trade.setType("un");
        trade.setBuyQuantity(10.0);

        //When
        when(tradeRepository.existsById(1)).thenReturn(true);
        when(tradeRepository.getById(1)).thenReturn(trade);

        //Then
        assertEquals(1, tradeService.getTradeById(1).getId());
        assertEquals("un", tradeService.getTradeById(1).getAccount());
        assertEquals("un", tradeService.getTradeById(1).getType());
        assertEquals(10.0, tradeService.getTradeById(1).getBuyQuantity());
    }

    @Test
    void TestGetTradeByIdWithBadId() throws Exception {

        //Given
        when(tradeRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> tradeService.getTradeById(20));
    }

    @Test
    void TestAddTradeWithNull() throws Exception {
        //Given
        when(tradeRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> tradeService.addTrade(null));
    }

    @Test
    void TestDeleteTradeByIdWithBadId() throws Exception {
        //Given
        when(tradeRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> tradeService.deleteTradeById(20));
    }

}
