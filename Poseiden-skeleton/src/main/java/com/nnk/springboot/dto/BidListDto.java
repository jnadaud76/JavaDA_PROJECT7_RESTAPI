package com.nnk.springboot.dto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

public class BidListDto {

    private Integer id;
    @NotBlank(message = "Account is mandatory")
    @Size(max = 30)
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Size(max = 30)
    private String type;
    @DecimalMin(value = "0.1", message = "Must be greater than or equal to 0.1 ")
    private Double bidQuantity;

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

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }
}
