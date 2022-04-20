package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

import java.util.List;

public interface IBidListService {

    List<BidListDto> getBidLists();

    void addBidList(BidList bidList);

    BidListDto getBidById(Integer id);

    void deleteBidById(Integer id);
}
