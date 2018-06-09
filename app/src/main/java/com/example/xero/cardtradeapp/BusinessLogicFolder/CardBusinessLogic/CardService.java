package com.example.xero.cardtradeapp.BusinessLogicFolder.CardBusinessLogic;

import android.util.JsonReader;
import android.util.Log;

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
            URL url = new URL("http://192.168.1.2:49912/api/Cards/"); //3719 //45455
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
                        switch (s){
                            case "Name":
                                card.setId(jsonReader.nextInt());
                                break;
                            case "Description":
                                card.setName(jsonReader.nextString());
                                break;
                            case "asd":
                                card.setName(jsonReader.nextString());
                                break;
                            case "das":
                                card.setName(jsonReader.nextString());
                                break;
                            case "fdg":
                                card.setName(jsonReader.nextString());
                                break;
                            case "nadfgme":
                                card.setName(jsonReader.nextString());
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
        }catch (Exception ex){Log.d("ERROR: ",ex.toString()+": "+ex.getMessage());return null;}
    }
}
