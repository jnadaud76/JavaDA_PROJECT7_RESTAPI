package com.nnk.springboot.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.time.LocalDateTime;


@Entity
@DynamicUpdate
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;
    @NotNull(message = "Must not be null")
    @Positive(message = "Must be at least 1")
    @Column(name= "Curve_Id")
    private Integer curveId;
    @Column(name= "as_Of_Date")
    private LocalDateTime asOfDate;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    @Column(name="term")
    private Double term;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    @Column(name="value")
    private Double value;
    @Column(name= "creation_Date")
    private LocalDateTime creationDate;

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

    public LocalDateTime getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(LocalDateTime asOfDate) {
        this.asOfDate = asOfDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
