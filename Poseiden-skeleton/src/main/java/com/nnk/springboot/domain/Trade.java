package com.nnk.springboot.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import java.sql.Timestamp;


@Entity
@DynamicUpdate
@Table(name = "trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Trade_Id")
    private Integer id;
    @Size(max=30)
    private String account;
    @Size(max=30)
    private String type;
    @Positive(message = "Must be at least 1")
    @Column(name="buy_Quantity")
    private Double buyQuantity;
    @Positive(message = "Must be at least 1")
    @Column(name="sell_Quantity")
    private Double sellQuantity;
    @Positive(message = "Must be at least 1")
    @Column(name="sell_Price")
    private Double sellPrice;
    @Size(max=125)
    private String benchmark;
    @Column(name="trade_Date")
    private Timestamp tradeDate;
    @Size(max=125)
    private String security;
    @Size(max=10)
    private String status;
    @Size(max=125)
    private String trader;
    @Size(max=125)
    private String book;
    @Size(max=125)
    @Column(name="creation_Name")
    private String creationName;
    @Column(name="creation_Date")
    private Timestamp creationDate;
    @Size(max=125)
    @Column(name="revision_Name")
    private String revisionName;
    @Column(name="revision_Date")
    private Timestamp revisionDate;
    @Size(max=125)
    @Column(name="deal_Name")
    private String dealName;
    @Size(max=125)
    @Column(name="deal_Type")
    private String dealType;
    @Size(max=125)
    @Column(name="source_List_Id")
    private String sourceListId;
    @Size(max=125)
    private String side;

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

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
