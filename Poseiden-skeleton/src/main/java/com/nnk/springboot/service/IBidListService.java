package com.nnk.springboot.service;

import com.nnk.springboot.dto.BidListDto;

import java.util.List;

public interface IBidListService {

    List<BidListDto> getBidLists();

    void addBidList(BidListDto bidListDto);

    BidListDto getBidById(Integer id);

    void deleteBidById(Integer id);
}
