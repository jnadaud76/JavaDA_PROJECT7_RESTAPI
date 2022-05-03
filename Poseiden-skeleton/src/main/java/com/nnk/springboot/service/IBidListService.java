package com.nnk.springboot.service;

import com.nnk.springboot.dto.BidListDto;

import java.util.List;

public interface IBidListService {

    List<BidListDto> getBidLists();

    Boolean addBidList(BidListDto bidListDto);

    BidListDto getBidById(Integer id);

    Boolean deleteBidById(Integer id);
}
