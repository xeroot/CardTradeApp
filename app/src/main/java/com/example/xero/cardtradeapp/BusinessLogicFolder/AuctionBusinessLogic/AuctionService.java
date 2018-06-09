package com.example.xero.cardtradeapp.BusinessLogicFolder.AuctionBusinessLogic;

import android.util.JsonReader;
import android.util.Log;

import com.example.xero.cardtradeapp.Entities.Auction;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AuctionService implements IAuctionService{


    @Override
    public List<Auction> getAuctions() {
        List<Auction> auctions = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try{
            URL url = new URL("http://10.0.2.2:45455/api/Auctions"); //3719 //45455
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStreamResponse = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReaderResponse = new InputStreamReader(inputStreamResponse,"UTF-8");
                JsonReader jsonReader = new JsonReader(inputStreamReaderResponse);
                jsonReader.beginArray();
                // parsing ...
                while(jsonReader.hasNext()){
                    jsonReader.beginObject();
                    Auction auction = new Auction();
                    while(jsonReader.hasNext()){
                        String s = jsonReader.nextName();
                        switch (s){
                            case "Id":
                                auction.setId(jsonReader.nextInt());
                                break;
                            case "CardName":
                                auction.setCardName(jsonReader.nextString());
                                break;
                            case "UsernameUserSeller ":
                                auction.setUsernameUserSeller(jsonReader.nextString());
                                break;
                            case "Type":
                                auction.setType(jsonReader.nextString());
                                break;
                            case "Amount":
                                auction.setAmount(jsonReader.nextDouble());
                                break;
                            case "CurrentAmount":
                                auction.setCurrentAmount(jsonReader.nextDouble());
                                break;
                            case "BeginDate":
                                auction.setBeginDate(formatter.parse(jsonReader.nextString()));
                                break;
                            case "EndDate":
                                auction.setEndDate(formatter.parse(jsonReader.nextString()));
                                break;
                            case "DescriptionCard":
                                auction.setDescriptionCard(jsonReader.nextString());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    auctions.add(auction);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.close();
                httpURLConnection.disconnect();
            }
            return auctions;
        }catch (Exception ex){
            Log.d("ERROR: ",ex.toString()+": "+ex.getMessage());return null;}
    }

    @Override
    public boolean putAuction(int idAuction, int idUser, double newcurrentAmount) {
        boolean result = false;
        try{
            URL url = new URL("http://10.0.2.2:45455/api/Auctions?id="+idAuction+"&idUser="+idUser);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("PUT");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","application/json");
            String resultJSON="";
            JSONObject jsonObject = new JSONObject();
            try{
                jsonObject.put("id",idAuction); // no es necesario peeerooo en el api esta asi >:(
                jsonObject.put("currentAmount",newcurrentAmount);
                resultJSON = jsonObject.toString();
            }catch (Exception ex){return false;}
            // enviando respuesta
            httpURLConnection.getOutputStream().write(resultJSON.getBytes());
            if(httpURLConnection.getResponseCode()==200) result = true;
            httpURLConnection.disconnect();
        }catch (Exception ex){Log.d("ERROR (putAuction): ",ex.toString()+": "+ex.getMessage());}
        return result;
    }
}
