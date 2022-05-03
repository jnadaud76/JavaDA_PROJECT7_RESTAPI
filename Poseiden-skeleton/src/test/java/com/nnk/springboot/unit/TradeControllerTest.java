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
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.service.ITradeService;
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
@WebMvcTest(controllers = TradeController.class)
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private ITradeService tradeService;

    @Test
    void TestHome() throws Exception {
        List<TradeDto> tradeDtos = new ArrayList<>();

        TradeDto tradeDto = new TradeDto();
        tradeDto.setId(1);
        tradeDto.setAccount("un");
        tradeDto.setType("un");
        tradeDto.setBuyQuantity(10.0);

        TradeDto tradeDto2 = new TradeDto();
        tradeDto2.setId(2);
        tradeDto2.setAccount("deux");
        tradeDto2.setType("deux");
        tradeDto2.setBuyQuantity(20.0);

        tradeDtos.add(tradeDto);
        tradeDtos.add(tradeDto2);
        when(tradeService.getTrades()).thenReturn(tradeDtos);
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trades"));
    }

    @Test
    void TestAddBidForm() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        List<TradeDto> tradeDtos = new ArrayList<>();

        TradeDto tradeDto = new TradeDto();
        tradeDto.setId(5);
        tradeDto.setAccount("cinq");
        tradeDto.setType("cinq");
        tradeDto.setBuyQuantity(50.0);

        tradeDtos.add(tradeDto);

        when(tradeService.getTrades()).thenReturn(tradeDtos);
        mockMvc.perform(post("/trade/validate")
                        .param("account", "cinq")
                        .param("type", "cinq")
                        .param("butQuantity", "50.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/trade/validate")
                        .param("account", "")
                        .param("type", "")
                        .param("buyQuantity", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        TradeDto tradeDto = new TradeDto();
        tradeDto.setId(1);
        tradeDto.setAccount("un");
        tradeDto.setType("un");
        tradeDto.setBuyQuantity(10.0);
        when(tradeService.getTradeById(1)).thenReturn(tradeDto);
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("trade"))
                .andExpect(view().name("trade/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/trade/update/20")));

    }

    @Test
    void TestUpdateBid() throws Exception {
        mockMvc.perform(post("/trade/update/2")
                        .param("account", "quatre")
                        .param("type", "quatre")
                        .param("buyQuantity", "40.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void TestUpdateBidWithBadArguments() throws Exception {
        mockMvc.perform(post("/trade/update/1")
                        .param("account", "")
                        .param("type", "")
                        .param("buyQuantity", "test"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    void TestDeleteBid() throws Exception {
        mockMvc.perform(get("/trade/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void TestDeleteBidWithBadId() throws Exception {
        when(tradeService.deleteTradeById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/trade/delete/20")));
    }
}
