package Lesson7;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String IconPhrase;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Temperature {
        private double Value;
        private String Unit;
        private int UnitType;

        @JsonGetter("Value")
        public double getValue() {
            return Value;
        }

        @JsonSetter("Value")
        public void setValue(double Value) {
            this.Value = Value;
        }

        public Temperature() {
            super();
        }
    }

    Temperature temperature;

    @JsonSetter("Temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public WeatherResponse() {
        super();
    }

    @JsonGetter("IconPhrase")
    public String getIconPhrase() {
        return IconPhrase;
    }

    @JsonSetter("IconPhrase")
    public void setIconPhrase(String iconPhrase) {
        IconPhrase = iconPhrase;
    }

    public static void main(String[] args) throws IOException {
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
                .addPathSegment("hourly")
                .addPathSegment("1hour")
                .addPathSegment("295212")
                .addQueryParameter("apikey", apiKey)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();

        //  String str = "[{\"DateTime\":\"2020-12-16T02:00:00+03:00\",\"EpochDateTime\":1608073200,\"WeatherIcon\":7,\"IconPhrase\":\"Cloudy\",\"HasPrecipitation\":false,\"IsDaylight\":false,\"Temperature\":{\"Value\":30.0,\"Unit\":\"F\",\"UnitType\":18},\"PrecipitationProbability\":47,\"MobileLink\":\"http://m.accuweather.com/en/ru/saint-petersburg/295212/hourly-weather-forecast/295212?day=1&hbhhour=2&lang=en-us\",\"Link\":\"http://www.accuweather.com/en/ru/saint-petersburg/295212/hourly-weather-forecast/295212?day=1&hbhhour=2&lang=en-us\"}]";
        List<WeatherResponse> weatherResponse = objectMapper.readValue(response.body().string(), new TypeReference<List<WeatherResponse>>() {
        });
        System.out.println("Погода сейчас в Санкт-Петербурге: " + weatherResponse.get(0).getIconPhrase());
        System.out.println("Градусов: " + (weatherResponse.get(0).temperature.getValue() - 32) + " Цельсия.");


    }

}