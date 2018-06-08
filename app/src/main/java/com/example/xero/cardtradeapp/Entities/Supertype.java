package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Supertype generated by hbm2java
 */
public class Supertype  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set cards = new HashSet(0);

    public Supertype() {
    }

	
    public Supertype(String name) {
        this.name = name;
    }
    public Supertype(String name, Set cards) {
       this.name = name;
       this.cards = cards;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getCards() {
        return this.cards;
    }
    
    public void setCards(Set cards) {
        this.cards = cards;
    }




}

