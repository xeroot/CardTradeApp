package com.example.xero.cardtradeapp.Entities;

public class Card {
    private int id;
    private String name;
    private String description;
    private double cost;
    private double minvalue;
    private int idUser;
    private String status;
    private int manaCost;
    private int power;
    private int toughness;
    private boolean isFoil;
    private int idRarity;
    private int idCategory;
    private int idSupertype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(double minvalue) {
        this.minvalue = minvalue;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness = toughness;
    }

    public boolean isFoil() {
        return isFoil;
    }

    public void setFoil(boolean foil) {
        isFoil = foil;
    }

    public int getIdRarity() {
        return idRarity;
    }

    public void setIdRarity(int idRarity) {
        this.idRarity = idRarity;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdSupertype() {
        return idSupertype;
    }

    public void setIdSupertype(int idSupertype) {
        this.idSupertype = idSupertype;
    }
}
