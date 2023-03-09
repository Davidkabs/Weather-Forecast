/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author dkaba
 */

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class WeatherForecast {
    
     private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s,%s&appid=" + API_KEY;

    
    public static void main(String[] args) {
         String cityName = "New York";
        String countryCode = "us";
        String url = String.format(API_URL, cityName, countryCode);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                JSONObject main = json.getJSONObject("main");
                double temp = main.getDouble("temp") - 273.15; // convert from Kelvin to Celsius
                double humidity = main.getDouble("humidity");

                System.out.printf("Temperature: %.2f°C\nHumidity: %.2f%%\n", temp, humidity);
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
