package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1



/**
 * RCardColor generated by hbm2java
 */
public class RCardColor  implements java.io.Serializable {


     private Integer id;
     private int idCard;
     private int idColor;

    public RCardColor() {
    }

    public RCardColor(int idCard, int idColor) {
       this.idCard = idCard;
       this.idColor = idColor;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdCard() {
        return this.idCard;
    }
    
    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
    public int getIdColor() {
        return this.idColor;
    }
    
    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }




}


