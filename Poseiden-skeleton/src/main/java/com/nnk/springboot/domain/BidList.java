package com.nnk.springboot.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "Bid_List_Id")
    private Integer id;
    @NotBlank(message = "Account is mandatory")
    @Length(max=30)
    private String account;
    @NotBlank(message = "Type is mandatory")
    @Size(max=30)
    private String type;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    @Column(name= "bid_Quantity")
    private Double bidQuantity;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    @Column(name= "ask_Quantity")
    private Double askQuantity;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    private Double bid;
    @DecimalMin(value="0.1", message="Must be greater than or equal to 0.1 ")
    private Double ask;
    @Size(max=125)
    private String benchmark;
    @Column(name= "bid_List_Date")
    private LocalDateTime bidListDate;
    @Size(max=125)
    private String commentary;
    @Size(max=125)
    private String security;
    @Size(max=10)
    private String status;
    @Size(max=125)
    private String trader;
    @Size(max=125)
    private String book;
    @Size(max=125)
    @Column(name= "creation_Name")
    private String creationName;
    @Column(name= "creation_Date")
    private Timestamp creationDate;
    @Size(max=125)
    @Column(name= "revision_Name")
    private String revisionName;
    @Column(name= "revision_Date")
    private LocalDateTime revisionDate;
    @Size(max=125)
    @Column(name= "deal_Name")
    private String dealName;
    @Size(max=125)
    @Column(name= "deal_Type")
    private String dealType;
    @Size(max=125)
    @Column(name= "source_List_Id")
    private String sourceListId;
    @Size(max=125)
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
