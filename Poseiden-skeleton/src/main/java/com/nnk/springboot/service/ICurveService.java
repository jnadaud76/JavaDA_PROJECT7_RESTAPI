package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;

import java.util.List;

public interface ICurveService {

    List<CurvePointDto> getCurvePoints();

   void addCurvePoint(CurvePoint curvePoint);

    CurvePointDto getCurvePointById(Integer id);

    void deleteCurvePointById(Integer id);
}
