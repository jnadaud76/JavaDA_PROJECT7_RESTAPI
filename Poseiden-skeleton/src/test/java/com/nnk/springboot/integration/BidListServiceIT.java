package com.nnk.springboot.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


import com.nnk.springboot.dto.BidListDto;

import com.nnk.springboot.service.BidListService;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;


import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
class BidListServiceIT {

    @Autowired
    private BidListService bidListService;

    @Test
    void TestGetBidLists() throws Exception {
        List<BidListDto> bidLists = bidListService.getBidLists();
        assertFalse(bidLists.isEmpty());

    }

    @Test
    void TestGetBidById() throws Exception {
        BidListDto bidListDto = bidListService.getBidById(1);
        assertEquals("un", bidListDto.getAccount());
        assertEquals("un", bidListDto.getType());
        assertEquals(200, bidListDto.getBidQuantity());
    }

    @Test
    void TestGetBidByIdWithBadId() throws Exception {
        assertThrows(IllegalArgumentException.class,
                () -> bidListService.getBidById(20));
    }

    @Test
    void TestAddBidList() throws Exception {
        BidListDto bidListDto = new BidListDto();
        bidListDto.setAccount("trois");
        bidListDto.setType("trois");
        bidListDto.setBidQuantity(300.0);

        bidListService.addBidList(bidListDto);

        assertEquals(3, bidListService.getBidLists().size());
        assertEquals(3, bidListService.getBidById(3).getId());
        assertEquals("trois", bidListService.getBidById(3).getAccount());
        assertEquals("trois", bidListService.getBidById(3).getType());
        assertEquals(300.0, bidListService.getBidById(3).getBidQuantity());
    }

    @Test
    void TestAddBidListWithNull() throws Exception {
        assertThrows(NullPointerException.class,
                () -> bidListService.addBidList(null));
    }

    @Test
    void TestDeleteBidById() throws Exception {
        bidListService.deleteBidById(2);

        assertThrows(IllegalArgumentException.class,
                () -> bidListService.getBidById(2));
    }

    @Test
    void TestDeleteBidByIdWithBadId() throws Exception {
        assertThrows(IllegalArgumentException.class,
                () -> bidListService.deleteBidById(20));
    }
}

