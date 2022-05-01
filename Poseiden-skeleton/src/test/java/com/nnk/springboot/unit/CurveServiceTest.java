package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class CurveServiceTest {

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Autowired
    private CurveService curveService;

    @Test
    void TestGetCurvePoints() throws Exception {
        //Given
        List<CurvePoint> curvePoints = new ArrayList<>();

        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        CurvePoint curvePoint2 = new CurvePoint();
        curvePoint2.setId(2);
        curvePoint2.setCurveId(2);
        curvePoint2.setTerm(30.0);
        curvePoint2.setValue(40.0);

        curvePoints.add(curvePoint);
        curvePoints.add(curvePoint2);

        //When
        when(curvePointRepository.findAll()).thenReturn(curvePoints);

        //Then
        assertFalse(curveService.getCurvePoints().isEmpty());
        assertEquals(2, curveService.getCurvePoints().size());
    }

    @Test
    void TestGetCurvePointById() throws Exception {
        //Given
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(1);
        curvePoint.setTerm(10.0);
        curvePoint.setValue(20.0);

        //When
        when(curvePointRepository.existsById(1)).thenReturn(true);
        when(curvePointRepository.getById(1)).thenReturn(curvePoint);

        //Then
        assertEquals(1, curveService.getCurvePointById(1).getId());
        assertEquals(1, curveService.getCurvePointById(1).getCurveId());
        assertEquals(10.0, curveService.getCurvePointById(1).getTerm());
        assertEquals(20.0, curveService.getCurvePointById(1).getValue());
    }

    @Test
    void TestGetCurvePointByIdWithBadId() throws Exception {

        //Given
        when(curvePointRepository.existsById(20)).thenReturn(false);

        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> curveService.getCurvePointById(20));
    }

    @Test
    void TestAddCurvePointWithNull() throws Exception {
        //Given
        when(curvePointRepository.save(null)).thenThrow(NullPointerException.class);
        //When Then
        assertThrows(NullPointerException.class,
                () -> curveService.addCurvePoint(null));
    }

    @Test
    void TestDeleteCurvePointByIdWithBadId() throws Exception {
        //Given
        when(curvePointRepository.existsById(20)).thenThrow(IllegalArgumentException.class);
        //When Then
        assertThrows(IllegalArgumentException.class,
                () -> curveService.deleteCurvePointById(20));
    }
}
