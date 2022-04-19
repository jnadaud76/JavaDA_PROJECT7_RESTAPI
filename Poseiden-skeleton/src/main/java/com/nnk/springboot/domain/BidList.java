package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "BidListId")
    private Integer id;
    @NotNull
    @Length(max=30)
    private String account;
    @NotNull
    @Length(max=30)
    private String type;
    @Column(name="bidQuantity")
    private Double bidQuantity;
    @Column(name="askQuantity")
    private Double askQuantity;

    private Double bid;

    private Double ask;
    @Length(max=125)
    private String benchmark;
    @Column(name="bidListDate")
    private LocalDateTime bidListDate;
    @Length(max=125)
    private String commentary;
    @Length(max=125)
    private String security;
    @Length(max=10)
    private String status;
    @Length(max=125)
    private String trader;
    @Length(max=125)
    private String book;
    @Length(max=125)
    @Column(name="creationName")
    private String creationName;
    @Column(name="creationDate")
    private Timestamp creationDate;
    @Length(max=125)
    @Column(name="revisionName")
    private String revisionName;
    @Column(name="revisionDate")
    private LocalDateTime revisionDate;
    @Length(max=125)
    @Column(name="dealName")
    private String dealName;
    @Length(max=125)
    @Column(name="dealType")
    private String dealType;
    @Length(max=125)
    @Column(name="sourceListId")
    private String sourceListId;
    @Length(max=125)
    private String side;

    public BidList() {
    }

    public BidList(String account, String type, Double bidQuantity) {
     this.account=account;
     this.type=type;
     this.bidQuantity=bidQuantity;

    }
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

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public LocalDateTime getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(LocalDateTime bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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

    public LocalDateTime getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(LocalDateTime revisionDate) {
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
