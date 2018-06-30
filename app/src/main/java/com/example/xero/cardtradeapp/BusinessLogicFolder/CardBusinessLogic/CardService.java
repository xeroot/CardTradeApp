package com.example.xero.cardtradeapp.BusinessLogicFolder.CardBusinessLogic;

import android.util.JsonReader;
import android.util.Log;

import com.example.xero.cardtradeapp.CONSTANTES;
import com.example.xero.cardtradeapp.Entities.Card;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardService implements ICardService {

    @Override
    public List<Card> getCards() {
        List<Card> cards = new ArrayList<>();
        try{
            CONSTANTES constantes = new CONSTANTES();
            URL url = new URL(constantes.getURLBASE()+"Cards/"); //3719 //45455
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if(httpURLConnection.getResponseCode()==200){
                InputStream inputStreamResponse = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReaderResponse = new InputStreamReader(inputStreamResponse,"UTF-8");
                JsonReader jsonReader = new JsonReader(inputStreamReaderResponse);
                jsonReader.beginArray();
                // parsing ...

                while(jsonReader.hasNext()){
                    jsonReader.beginObject();
                    Card card = new Card();
                    while(jsonReader.hasNext()){
                        String s = jsonReader.nextName();
                        switch (s.toLowerCase()){
                            case "id":
                                card.setId(jsonReader.nextInt());
                                break;
                            case "name":
                                card.setName(jsonReader.nextString());
                                break;
                            case "description":
                                card.setDescription(jsonReader.nextString());
                                break;
                            case "cost":
                                card.setCost(jsonReader.nextDouble());
                                break;
                            case "minvalue":
                                card.setMinvalue(jsonReader.nextDouble());
                                break;
                            case "manacost":
                                card.setManaCost(jsonReader.nextInt());
                                break;
                            case "power":
                                try {
                                    card.setPower(jsonReader.nextInt());
                                }catch (Exception e){
                                    card.setPower(0);
                                    jsonReader.skipValue();
                                }
                                break;
                            case "toughtness":
                                try{
                                    card.setToughness(jsonReader.nextInt());
                                }catch (Exception e){
                                    jsonReader.skipValue();
                                    card.setToughness(0);
                                }

                                break;
                            case "IsFoil":
                                card.setFoil(jsonReader.nextBoolean());
                                break;
                            default:
                                jsonReader.skipValue();
                                break;
                        }
                    }
                    cards.add(card);
                    jsonReader.endObject();
                }
                jsonReader.endArray();
                jsonReader.close();
                httpURLConnection.disconnect();
            }
            return cards;
        }catch (Exception ex)
        {Log.d("ERROR: ",ex.toString()+": "+ex.getMessage());return null;}
    }
}
