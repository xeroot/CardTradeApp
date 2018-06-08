package com.example.xero.cardtradeapp.Entities;
// Generated May 12, 2018 6:06:40 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String pass;
     private Set profiles = new HashSet(0);
     private Set auctionsForIdUserSeller = new HashSet(0);
     private Set auctionsForIdCurrentUser = new HashSet(0);
     private Set transactions = new HashSet(0);

    public User() {
    }

	
    public User(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }
    public User(String username, String pass, Set profiles, Set auctionsForIdUserSeller, Set auctionsForIdCurrentUser, Set transactions) {
       this.username = username;
       this.pass = pass;
       this.profiles = profiles;
       this.auctionsForIdUserSeller = auctionsForIdUserSeller;
       this.auctionsForIdCurrentUser = auctionsForIdCurrentUser;
       this.transactions = transactions;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPass() {
        return this.pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    public Set getProfiles() {
        return this.profiles;
    }
    
    public void setProfiles(Set profiles) {
        this.profiles = profiles;
    }
    public Set getAuctionsForIdUserSeller() {
        return this.auctionsForIdUserSeller;
    }
    
    public void setAuctionsForIdUserSeller(Set auctionsForIdUserSeller) {
        this.auctionsForIdUserSeller = auctionsForIdUserSeller;
    }
    public Set getAuctionsForIdCurrentUser() {
        return this.auctionsForIdCurrentUser;
    }
    
    public void setAuctionsForIdCurrentUser(Set auctionsForIdCurrentUser) {
        this.auctionsForIdCurrentUser = auctionsForIdCurrentUser;
    }
    public Set getTransactions() {
        return this.transactions;
    }
    
    public void setTransactions(Set transactions) {
        this.transactions = transactions;
    }




}


