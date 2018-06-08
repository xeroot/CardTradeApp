package com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic;

import com.example.xero.cardtradeapp.Entities.Auction;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AuctionService implements IAuctionService {
    @Override
    public Boolean pushAuction(Auction auction) {

        Boolean result = false;
        //Default new item with status Created
        auction.setStatus("Created");
        try {
            URL apiUrl =
                    new URL("http://192.168.1.2:49912/api/Auctions");

            // Create connection
            HttpURLConnection myConnection =
                    (HttpURLConnection) apiUrl.openConnection();

            //Set method type
            myConnection.setRequestMethod("POST");

            myConnection.setDoOutput(true);

            //Set data
            myConnection.setRequestProperty("Content-Type", "application/json");
            myConnection.setRequestProperty("Accept", "application/json");
            String itemJson = auction.toJson();
            myConnection.getOutputStream().write(itemJson.getBytes());

            //Process response
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
                result = true;
            }
            myConnection.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;


    }
}