package com.example.xero.cardtradeapp.Entities;

import java.util.Date;

public class Auction {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getUsernameUserSeller() {
        return usernameUserSeller;
    }

    public void setUsernameUserSeller(String usernameUserSeller) {
        this.usernameUserSeller = usernameUserSeller;
    }

    public String getDescriptionCard() {
        return descriptionCard;
    }

    public void setDescriptionCard(String descriptionCard) {
        this.descriptionCard = descriptionCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private int id;
    private String cardName;
    private String descriptionCard;
    private String usernameUserSeller;
    private Date beginDate;
    private Date endDate;
    private String status;
    private String type;
    private double currentAmount;
    private double amount;


    /*
    public void ParseFromJson(String val){
        switch (val){
            case "Name":
                auction.setId(jsonReader.nextInt());
                break;
            case "Description":
                auction.setName(jsonReader.nextString());
                break;
            case "asd":
                auction.setName(jsonReader.nextString());
                break;
            case "das":
                auction.setName(jsonReader.nextString());
                break;
            case "fdg":
                auction.setName(jsonReader.nextString());
                break;
            case "nadfgme":
                auction.setName(jsonReader.nextString());
                break;
            default:
                jsonReader.skipValue();
                break;
        }
    }
    */

}
