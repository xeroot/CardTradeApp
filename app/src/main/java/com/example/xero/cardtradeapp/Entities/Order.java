package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Order generated by hbm2java
 */
public class Order  implements java.io.Serializable {


     private Integer id;
     private Auction auction;
     private boolean isReviced;
     private Date dateRecived;
     private String status;
     private Date beginDate;
     private Date endDate;
     private String shippingMethod;

    public Order() {
    }

	
    public Order(Auction auction, boolean isReviced, String status, Date beginDate, Date endDate, String shippingMethod) {
        this.auction = auction;
        this.isReviced = isReviced;
        this.status = status;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.shippingMethod = shippingMethod;
    }
    public Order(Auction auction, boolean isReviced, Date dateRecived, String status, Date beginDate, Date endDate, String shippingMethod) {
       this.auction = auction;
       this.isReviced = isReviced;
       this.dateRecived = dateRecived;
       this.status = status;
       this.beginDate = beginDate;
       this.endDate = endDate;
       this.shippingMethod = shippingMethod;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Auction getAuction() {
        return this.auction;
    }
    
    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    public boolean isIsReviced() {
        return this.isReviced;
    }
    
    public void setIsReviced(boolean isReviced) {
        this.isReviced = isReviced;
    }
    public Date getDateRecived() {
        return this.dateRecived;
    }
    
    public void setDateRecived(Date dateRecived) {
        this.dateRecived = dateRecived;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getBeginDate() {
        return this.beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getShippingMethod() {
        return this.shippingMethod;
    }
    
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }




}

