/*
Name: Jeffrey Wilson
Date: 6/28/21
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.net.URLConnection;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class WeatherCall {
    //Method to convert map to string and uses jsontomap and gson to translate
    public static Map<String, Object> jsonToMap(String str) {
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }
    //Main method
    public static void main(String[] args) {
        //Create Scanner
        Scanner input = new Scanner(System.in);
        //Get user input
        System.out.println("Enter a city: ");
        //Store user input
        String City = input.nextLine();
        //API Key to access the website's weather
        String API_KEY = "ee75525098711335eeb04cccc853fdeb";
        //URL string to get the info
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + City + "&appid=" + API_KEY + "&units=imperial";
        try {
            //Necessary variables
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            //While line is valid, continue to append the info
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            //Map objects for necessary information, also parses json to map
            Map<String, Object> respMap = jsonToMap(result.toString());
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString());
            Map<String, Object> windMap = jsonToMap(respMap.get("wind").toString());

            //Displays Current temp, humidity, wind speeds, and angle from user city
            System.out.println("Current Temperature: " + mainMap.get("temp"));
            System.out.println("Current Humidity: " + mainMap.get("humidity"));
            System.out.println("Wind Speeds: " + windMap.get("speed"));
            System.out.println("Wind Angle: " + windMap.get("deg"));

            //Necessary catch blocks
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

}

