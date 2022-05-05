package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.service.IBidListService;
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
@WebMvcTest(controllers = BidListController.class)
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private IBidListService bidListService;

    @Test
    void TestHome() throws Exception {
        List<BidListDto> bidListDtos = new ArrayList<>();

        BidListDto bidListDto = new BidListDto();
        bidListDto.setId(1);
        bidListDto.setAccount("test");
        bidListDto.setType("test");
        bidListDto.setBidQuantity(10.0);

        BidListDto bidListDto2 = new BidListDto();
        bidListDto2.setId(2);
        bidListDto2.setAccount("test1");
        bidListDto2.setType("test1");
        bidListDto2.setBidQuantity(20.0);

        bidListDtos.add(bidListDto);
        bidListDtos.add(bidListDto2);

        when(bidListService.getBidLists()).thenReturn(bidListDtos);
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    void TestAddBidForm() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        List<BidListDto> bidListDtos = new ArrayList<>();

        BidListDto bidListDto = new BidListDto();
        bidListDto.setId(5);
        bidListDto.setAccount("cinq");
        bidListDto.setType("cinq");
        bidListDto.setBidQuantity(600.0);

        bidListDtos.add(bidListDto);

        when(bidListService.getBidLists()).thenReturn(bidListDtos);

        mockMvc.perform(post("/bidList/validate")
                        .param("account", "cinq")
                        .param("type", "cinq")
                        .param("bidQuantity", "600.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/bidList/validate")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", "300.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        BidListDto bidListDto = new BidListDto();
        bidListDto.setId(1);
        bidListDto.setAccount("un");
        bidListDto.setType("un");
        bidListDto.setBidQuantity(200.0);
        when(bidListService.getBidById(1)).thenReturn(bidListDto);
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("bidList"))
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/bidList/update/20")));

    }

    @Test
    void TestUpdateBid() throws Exception {
        mockMvc.perform(post("/bidList/update/2")
                        .param("account", "quatre")
                        .param("type", "quatre")
                        .param("bidQuantity", "250.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void TestUpdateBidWithBadArguments() throws Exception {
        mockMvc.perform(post("/bidList/update/1")
                        .param("account", "")
                        .param("type", "")
                        .param("bidQuantity", "300.0"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void TestDeleteBid() throws Exception {
        mockMvc.perform(get("/bidList/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void TestDeleteBidWithBadId() throws Exception {
        when(bidListService.deleteBidById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/bidList/delete/20")));
    }
}
