package com.nnk.springboot.util;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.dto.CurvePointDto;

public interface IConversion {

    BidListDto bidListToBidListDto(BidList bidList);

    CurvePointDto curvePointToCurvePointDto (CurvePoint curvePoint);
}
