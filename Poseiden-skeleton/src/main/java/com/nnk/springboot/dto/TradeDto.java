package com.nnk.springboot.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TradeDto {

    private Integer id;
    @Size(max=30)
    private String account;
    @Size(max=30)
    private String type;
    @Positive(message = "Must be at least 1")
    private Double buyQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
