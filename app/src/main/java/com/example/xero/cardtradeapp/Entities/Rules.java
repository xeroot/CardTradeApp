package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1



/**
 * Rules generated by hbm2java
 */
public class Rules  implements java.io.Serializable {


     private Integer id;
     private String description;
     private String type;

    public Rules() {
    }

    public Rules(String description, String type) {
       this.description = description;
       this.type = type;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }




}

