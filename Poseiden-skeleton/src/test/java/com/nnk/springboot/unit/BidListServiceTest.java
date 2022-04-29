package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
class BidListServiceTest {

    @MockBean
    private BidListRepository bidListRepository;

    @Autowired
    private BidListService bidListService;

    @Test
    void TestGetBidLists() throws Exception {
        //Given
        List<BidList> bidLists = new ArrayList<>();

        BidList bidList = new BidList();
        bidList.setId(1);
        bidList.setAccount("test");
        bidList.setType("test");
        bidList.setBidQuantity(10.0);

        BidList bidList2 = new BidList();
        bidList2.setId(2);
        bidList2.setAccount("test1");
        bidList2.setType("test1");
        bidList2.setBidQuantity(20.0);

        bidLists.add(bidList);
        bidLists.add(bidList2);

        //When
        when(bidListRepository.findAll()).thenReturn(bidLists);

        //Then
        assertFalse(bidListService.getBidLists().isEmpty());
        assertEquals(2, bidListService.getBidLists().size());
    }

    @Test
    void TestGetBidById() throws Exception {
        //Given
        BidList bidList = new BidList();
        bidList.setId(1);
        bidList.setAccount("test");
        bidList.setType("test");
        bidList.setBidQuantity(10.0);

        //When
        when(bidListRepository.existsById(1)).thenReturn(true);
        when(bidListRepository.getById(1)).thenReturn(bidList);

        //Then
        assertEquals(1, bidListService.getBidById(1).getId());
        assertEquals("test", bidListService.getBidById(1).getAccount());
        assertEquals("test", bidListService.getBidById(1).getType());
        assertEquals(10.0, bidListService.getBidById(1).getBidQuantity());
    }

    @Test
    void TestGetBidByIdWithBadId() throws Exception {

        //Given
        when(bidListRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> bidListService.getBidById(20));
    }

    @Test
    void TestAddBidListWithNull() throws Exception {
        //Given
        when(bidListRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> bidListService.addBidList(null));
    }

    @Test
    void TestDeleteBidByIdWithBadId() throws Exception {
        //Given
        when(bidListRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> bidListService.deleteBidById(20));
    }
}
