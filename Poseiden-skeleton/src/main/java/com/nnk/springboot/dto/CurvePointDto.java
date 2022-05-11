package com.nnk.springboot.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CurvePointDto {

    private Integer id;
    @NotNull(message = "Must not be null")
    @Positive(message = "Must be at least 1")
    private Integer curveId;
    @DecimalMin(value = "0.1", message = "Must be greater than or equal to 0.1 ")
    private Double term;
    @DecimalMin(value = "0.1", message = "Must be greater than or equal to 0.1 ")
    private Double value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
