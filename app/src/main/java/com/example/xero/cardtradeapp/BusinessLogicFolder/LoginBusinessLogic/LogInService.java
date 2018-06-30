package com.example.xero.cardtradeapp.BusinessLogicFolder.LoginBusinessLogic;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.support.v4.util.Pair;
import android.util.Log;

import com.example.xero.cardtradeapp.CONSTANTES;

public class LogInService implements  ILogInService {
    @Override
    public Pair<Boolean,Integer> UserValid(String username, String password) {
        Boolean isValid = false;
        int userId= 0;
        CONSTANTES constantes = new CONSTANTES();
        try {
            String url = constantes.getURLBASE()+"Users?username="+username+"&password="+password;
            URL apiUrl = new URL(url);

            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) apiUrl.openConnection();

            //Process response
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here

                //Read response
                InputStream responseBody = myConnection.getInputStream();

                //Use reader for response
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                //Read json
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                jsonReader.beginObject();
                while (jsonReader.hasNext()){
                    switch (jsonReader.nextName()){
                        case "Id":
                            userId = jsonReader.nextInt();
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.endObject();
                jsonReader.close();
                myConnection.disconnect();
            } else {
                // Error handling code goes here
            }
        }
        catch (Exception ex){Log.d("ERROR (UserValid): ",ex.toString()+": "+ex.getMessage());return null;}
        if(userId!=0) isValid = true;
        Pair<Boolean,Integer> pair = new Pair<>(isValid,userId);
        return pair;
    }
}