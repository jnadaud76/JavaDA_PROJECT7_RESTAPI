package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.util.IConversion;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurveService implements ICurveService{

    private final CurvePointRepository curvePointRepository;

    private final IConversion conversion;

    public CurveService(CurvePointRepository curvePointRepository, IConversion conversion) {
        this.curvePointRepository = curvePointRepository;
        this.conversion = conversion;
    }

    public List<CurvePointDto> getCurvePoints() {
        List<CurvePoint> curvePoints = curvePointRepository.findAll();
        return curvePoints.stream()
                .map(conversion::curvePointToCurvePointDto)
                .collect(Collectors.toList());
    }

    public void addCurvePoint(CurvePointDto curvePointDto){
        curvePointRepository.save(conversion.curvePointDtoToCurvePoint(curvePointDto));

    }

    public CurvePointDto getCurvePointById(Integer id){
        if(curvePointRepository.existsById(id)){
            return conversion.curvePointToCurvePointDto(curvePointRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid curvePoint Id:" + id);
        }

    }

    public void deleteCurvePointById(Integer id){
        if(curvePointRepository.existsById(id)){
            curvePointRepository.delete(curvePointRepository.getById(id));
        } else {
            throw new IllegalArgumentException("Invalid curvePoint Id:" + id);
        }

    }
}
