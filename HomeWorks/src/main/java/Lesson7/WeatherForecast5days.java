package Lesson7;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class WeatherForecast5days {
    public static HashMap<String, String> locations = new HashMap<String, String>() {
        {
            put("295212", "Saint-Petersburg");
            put("60449", "Santiago");
            put("178087", "Berlin");
            put("308526", "Madrid");
            put("328328", "London");
        }
    };

    public static class LocationForecast {
        String date;
        String locationKey;
        String weatherText;
        double temperature;

        public LocationForecast(String date, String locationKey, String weatherText, double temperature) {
            this.date = date;
            this.locationKey = locationKey;
            this.weatherText = weatherText;
            this.temperature = temperature;
        }
    }

    public static ArrayList<LocationForecast> getForecast5days(String locationKey) throws IOException {
        String apiKey = "txhbc8gx91go8XwoIT6uzKMAx9soaXlU";

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("dataservice.accuweather.com")
                .addPathSegment("forecasts")
                .addPathSegment("v1")
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(locationKey)
                .addQueryParameter("apikey", apiKey)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<LocationForecast> result = new ArrayList<>();

        for (Integer currentDay = 0; currentDay < 5; currentDay++) {
            JsonNode dailyForecast = objectMapper
                .readTree(jsonResponse)
                .at(("/DailyForecasts/"+ currentDay.toString()));

        JsonNode date = dailyForecast.get("Date");
        JsonNode weatherText = dailyForecast.get("Day").get("IconPhrase");
        JsonNode temperature = dailyForecast.get("Temperature").get("Minimum").get("Value");

        LocationForecast locationForecast = new LocationForecast(date.asText(), locationKey, weatherText.asText(), temperature.asDouble() - 32.0);
        result.add(locationForecast); }
        return result;
    }

    public static void main(String[] args) throws IOException {
        for (String locationKey : locations.keySet()) {
            System.out.println();
            for (LocationForecast locationForecast : getForecast5days(locationKey)) {
                System.out.println("В городе " + locations.get(locationKey) + "  на дату  " + locationForecast.date + "  ожидается погода: "
                        + locationForecast.weatherText + ",  температура: " + locationForecast.temperature + ".");
            }
        }
    }
}
