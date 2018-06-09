package com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Auction;

import java.util.List;

public interface IAuctionService {
    List<Auction> getAuctions();
    boolean putAuction(int idAuction, int idUser, double newcurrentAmount);
    boolean pushAuction(Auction auction);
}
