package com.example.xero.cardtradeapp.BusinessLogicFolder.OrderBusinessLogic;


import android.util.JsonReader;

import com.example.xero.cardtradeapp.Entities.Order;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class OrderService implements  IOrderService {
    @Override
    public ArrayList<Order> GetOrders(int idUser) {
        ArrayList<Order> orders = null;
        URL apiUrl = null;

        try {

            apiUrl = new URL("http://192.168.1.2:49912/api/Orders");
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here

                //Read Response
                InputStream responseBody = myConnection.getInputStream();
                // Read Json for response
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");

                JsonReader jsonReader = new JsonReader(responseBodyReader);

                //start reading array
                jsonReader.beginArray();

                //read elements
                orders = new ArrayList<>();
                //read every


                int id=0;
                Boolean isRecived=false;
                String dateRecived="";
                String status="";
                int idAuction=0;
                String beginDate="";
                String endDate="";
                String shippingMethod="";
                String cardName="";
                double cost=0.0;
                String buyerOrSeller="";
                int idUserSeller=0;

                while(jsonReader.hasNext()){
                    //Read every object
                    jsonReader.beginObject();
                    String description = null;
                    while(jsonReader.hasNext()){
                        String property = jsonReader.nextName();
                        switch (property.toLowerCase()){
                            case "id":
                                id = jsonReader.nextInt();
                                break;
                            case "isrecived":
                                isRecived = jsonReader.nextBoolean();
                                break;
                            case "daterecived":
                                try {
                                    dateRecived = jsonReader.nextString();
                                    dateRecived=dateRecived.split("T")[0];
                                }
                                catch (Exception e){
                                    dateRecived="";
                                    jsonReader.skipValue();
                                }
                                break;
                            case "status":
                                status = jsonReader.nextString();
                                break;
                            case "idauction":
                                idAuction = jsonReader.nextInt();
                                break;
                            case "begindate":
                                beginDate = jsonReader.nextString();
                                beginDate=beginDate.split("T")[0];
                                break;
                            case "enddate":
                                endDate = jsonReader.nextString();
                                endDate=endDate.split("T")[0];
                                break;
                            case "shippingmethod":
                                shippingMethod = jsonReader.nextString();
                                break;
                            case "cardname":
                                cardName = jsonReader.nextString();
                                break;
                            case "cost":
                                try {
                                    cost = jsonReader.nextLong();
                                }
                                catch (Exception e){
                                    cost=0.0;
                                    jsonReader.skipValue();
                                }
                                break;
                            case "iduserseller":
                                idUserSeller = jsonReader.nextInt();
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    if(idUser==idUserSeller){
                        buyerOrSeller = "Seller";
                    }
                    else{
                        buyerOrSeller = "Buyer";
                    }
                    //Add item to the list
                    Order objOrder =
                            new Order(id,idAuction, isRecived,dateRecived,status,beginDate,endDate,shippingMethod,cardName,cost,buyerOrSeller,idUserSeller);
                    orders.add(objOrder);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.close();
                myConnection.disconnect();




            } else {
                // Error handling code goes here

            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return orders;





    }

    @Override
    public Order getOrder(int idorder, String type) {
        Order objOrder=null;

        URL apiUrl = null;
        try {

            apiUrl = new URL("http://192.168.1.2:49912/api/Orders?idOrder="+String.valueOf(idorder)+"&type="+type);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here

                //Read Response
                InputStream responseBody = myConnection.getInputStream();
                // Read Json for response
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");

                JsonReader jsonReader = new JsonReader(responseBodyReader);




                String cardName="";
                double cardCost=0.0;
                String endDate="";
                String shippingMethod="";
                String ContactName="";
                String phoneNumber="";
                Boolean isRecived=false;


                    //Read every object
                    jsonReader.beginObject();
                    String description = null;
                    while(jsonReader.hasNext()){
                        String property = jsonReader.nextName();
                        switch (property.toLowerCase()){
                           case "nameuserbuyer":try {
                                ContactName = jsonReader.nextString();
                            }catch (Exception e){
                                jsonReader.skipValue();
                            }
                                break;
                            case "nameuserseller":try {
                                ContactName = jsonReader.nextString();
                            }catch (Exception e){
                                jsonReader.skipValue();
                            }
                            case "phoneuserbuyer":try {
                                phoneNumber = jsonReader.nextString();
                            }catch (Exception e){
                                jsonReader.skipValue();
                            }
                                break;
                            case "phoneuserseller":try {
                                phoneNumber = jsonReader.nextString();
                            }catch (Exception e){
                                jsonReader.skipValue();
                            }
                            case "enddate":
                                endDate = jsonReader.nextString();
                                endDate=endDate.split("T")[0];
                                break;
                            case "isrecived":
                                isRecived = jsonReader.nextBoolean();
                                break;
                            case "shippingmethod":
                                shippingMethod = jsonReader.nextString();
                                break;
                            case "cardname":
                                cardName = jsonReader.nextString();
                                break;
                            case "cost":
                                try {
                                    cardCost = jsonReader.nextLong();
                                }
                                catch (Exception e){
                                    cardCost=0.0;
                                    jsonReader.skipValue();
                                }
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }


                    //Add item to the list
                    objOrder =
                            new Order();
                        objOrder.setCardName(cardName);
                        objOrder.setCost(cardCost);
                        objOrder.setEndDate(endDate);
                        objOrder.setShippingMethod(shippingMethod);
                        objOrder.setContactName(ContactName);
                        objOrder.setContactPhone(phoneNumber);
                        objOrder.setIsReviced(isRecived);
                    jsonReader.endObject();
                }

                jsonReader.close();
                myConnection.disconnect();




            } else {
                // Error handling code goes here

            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return objOrder;




    }
}

