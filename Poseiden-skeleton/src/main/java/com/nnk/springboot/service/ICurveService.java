package com.nnk.springboot.service;

import com.nnk.springboot.dto.CurvePointDto;

import java.util.List;

public interface ICurveService {

    List<CurvePointDto> getCurvePoints();

    Boolean addCurvePoint(CurvePointDto curvePointDto);

    CurvePointDto getCurvePointById(Integer id);

    Boolean deleteCurvePointById(Integer id);
}
