package com.example.weather.service;

import com.fasterxml.jackson.core.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Service
public class DiaryService {

    @Value("${openweathermap.key}")
    private String apiKey;

    public void createDiary(LocalDate date, String text) {
        String weatherData = getWeatherString();
        Map<String,Object> parseWeather = parseWeather(weatherData);
    }

    private String getWeatherString() {
       String apiUrl =  "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;
       System.out.println(apiUrl);
       try{
           URL url = new URL(apiUrl);
           HttpURLConnection connection = (HttpURLConnection) url.openConnection();
           connection.setRequestMethod("GET");
           int responseCode = connection.getResponseCode();
           BufferedReader br;
           if(responseCode == 200){
               br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
           }else {
               br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
           }
           String inputLine;
           StringBuilder response = new StringBuilder();
           while((inputLine = br.readLine()) != null){
               System.out.println(inputLine);
               response.append(inputLine);
           }br.close();
           //System.out.println(response.toString());
           return response.toString();

       }catch(Exception ex){
           return "failed to get response";
       }
    }

    private Map<String, Object> parseWeather(String jsonStirng){
        JSONParser jsonParser = new JSONParser();

        JSONObject jsonObject;
        try{
            jsonObject =(JSONObject) jsonParser.parse(jsonStirng);
        } catch(ParseException e){
            throw new RuntimeException();
        }
        Map<String,Object> resultMap = new HashMap<>();
        JSONObject mainData = (JSONObject) jsonObject.get("weather");
        resultMap.put("temp", mainData.get("temp"));
        JSONObject weatherData = (JSONObject) jsonObject.get("main");
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));
        return resultMap;


    }
}

