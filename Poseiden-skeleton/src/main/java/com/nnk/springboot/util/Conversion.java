package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

import org.springframework.stereotype.Component;

@Component
public class Conversion implements IConversion{

    public BidListDto bidListToBidListDto(final BidList bidList) {
        BidListDto bidListFullDto = new BidListDto();
        bidListFullDto.setId(bidList.getId());
        bidListFullDto.setAccount(bidList.getAccount());
        bidListFullDto.setType(bidList.getType());
        bidListFullDto.setBidQuantity(bidList.getBidQuantity());
        return bidListFullDto;
    }
}
