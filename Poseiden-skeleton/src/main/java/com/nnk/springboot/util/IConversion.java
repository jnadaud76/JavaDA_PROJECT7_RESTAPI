package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

public interface IConversion {

    BidListDto bidListToBidListDto(BidList bidList);
}
