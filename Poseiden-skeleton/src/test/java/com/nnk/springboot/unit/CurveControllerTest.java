package com.nnk.springboot.unit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.service.ICurveService;
import com.nnk.springboot.service.MyUserDetailsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;

@WithMockUser
@WebMvcTest(controllers = CurveController.class)
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private ICurveService curveService;

    @Test
    void TestHome() throws Exception {
        List<CurvePointDto> curvePointDtos = new ArrayList<>();

        CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(1);
        curvePointDto.setCurveId(1);
        curvePointDto.setTerm(10.0);
        curvePointDto.setValue(20.0);

        CurvePointDto curvePointDto2 = new CurvePointDto();
        curvePointDto2.setId(2);
        curvePointDto2.setCurveId(2);
        curvePointDto2.setTerm(30.0);
        curvePointDto2.setValue(40.0);

        curvePointDtos.add(curvePointDto);
        curvePointDtos.add(curvePointDto2);

        when(curveService.getCurvePoints()).thenReturn(curvePointDtos);
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoints"));
    }

    @Test
    void TestAddCurveForm() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk());
    }

    @Test
    void TestValidate() throws Exception {
        List<CurvePointDto> curvePointDtos = new ArrayList<>();

        CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(5);
        curvePointDto.setCurveId(5);
        curvePointDto.setTerm(90.0);
        curvePointDto.setValue(100.0);

        curvePointDtos.add(curvePointDto);

        when(curveService.getCurvePoints()).thenReturn(curvePointDtos);

        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "5")
                        .param("term", "90.0")
                        .param("value", "100.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));

    }

    @Test
    void TestValidateWithBadArguments() throws Exception {

        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "")
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));

    }

    @Test
    void TestShowUpdateForm() throws Exception {
        CurvePointDto curvePointDto = new CurvePointDto();
        curvePointDto.setId(1);
        curvePointDto.setCurveId(1);
        curvePointDto.setTerm(10.0);
        curvePointDto.setValue(20.0);
        when(curveService.getCurvePointById(1)).thenReturn(curvePointDto);
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("curvePoint"))
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void TestShowUpdateFormWithBadId() throws Exception {
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/curvePoint/update/20")));

    }

    @Test
    void TestUpdateCurve() throws Exception {
        mockMvc.perform(post("/curvePoint/update/2")
                        .param("curveId", "4")
                        .param("term", "110.0")
                        .param("value", "110.0"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void TestUpdateCurveWithBadArguments() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "")
                        .param("term", "")
                        .param("value", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    @Test
    void TestDeleteCurve() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void TestDeleteCurveWithBadId() throws Exception {
        when(curveService.deleteCurvePointById(20)).thenThrow(new IllegalArgumentException());
        assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/curvePoint/delete/20")));
    }

}
