package Lesson7;

import Lesson7.entity.WeatherData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import Lesson7.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AccuWeatherProvider implements WeatherProvider {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST_ENDPOINT = "forecasts";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "currentconditions";
    private static final String API_VERSION = "v1";
    private static final String API_KEY = ApplicationGlobalState.getInstance().getApiKey();

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    public String cityName;
    public String countryName;

    @Override
    public void getWeather(Periods periods) throws IOException, SQLException {
        String cityKey = detectCityKey();

        if (periods.equals(Periods.NOW)) {
            List<WeatherResponse> weatherResponse = getCurrentWeather(cityKey);

            printCurrentWeather(weatherResponse);
            writeToBD(weatherResponse);


        } else if (periods.equals(Periods.FIVE_DAYS)) {
            List<WeatherResponse> weatherResponse = get5daysWeather(cityKey);
            writeToBD(weatherResponse);
            printForecast5Days(weatherResponse);

        } else {
            System.out.println("Вывод прогноза на другой период времени пока недоступен.");
        }
    }

    private void writeToBD(List<WeatherResponse> weatherResponse) throws SQLException {
        DatabaseRepositorySQLiteImpl databaseRepositorySQLite = new DatabaseRepositorySQLiteImpl();
        for (WeatherResponse forecast1Day : weatherResponse) {
            WeatherData weatherData = new WeatherData(cityName, forecast1Day.date, forecast1Day.getWeatherText(), forecast1Day.temperature.metric.getValue());
            databaseRepositorySQLite.saveWeatherData(weatherData);
        }
    }

    @Override
    public void getAllFromDb() throws IOException, SQLException {
        DatabaseRepositorySQLiteImpl databaseRepositorySQLite = new DatabaseRepositorySQLiteImpl();
        databaseRepositorySQLite.getAllSavedData();
    }

    public void printCurrentWeather(List<WeatherResponse> weatherResponse) {
        System.out.println(weatherResponse);
        System.out.println("Погода сейчас в городе " + cityName + " в стране " + countryName + ": " + weatherResponse.get(0).getWeatherText());
        System.out.println("Температура воздуха: " + weatherResponse.get(0).temperature.metric.getValue() + " градусов цельсия.");
        System.out.println();
    }

    public void printForecast5Days(List<WeatherResponse> weatherResponse) {
        for (WeatherResponse forecast1Day : weatherResponse) {
            System.out.println("В городе " + cityName + " в стране " + countryName + "  на дату  " + forecast1Day.date + "  ожидается погода: "
                    + forecast1Day.getWeatherText() + ",  температура: " + (forecast1Day.temperature.metric.getValue() - 32) + ".");
        }
    }

    public List<WeatherResponse> getCurrentWeather(String cityKey) throws IOException {
        List<WeatherResponse> result = new ArrayList<>();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(CURRENT_CONDITIONS_ENDPOINT)
                .addPathSegment(API_VERSION)
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        System.out.println(jsonResponse);
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode dailyForecasts = objectMapper.readTree(jsonResponse);
        for (JsonNode dailyForecast : dailyForecasts) {
            JsonNode weatherText = null;
            JsonNode temperature = null;
            String date = response.header("Date");
            System.out.println(date);
            if (dailyForecast.has("WeatherText")) {
                weatherText = dailyForecast.get("WeatherText");
            }
            if (dailyForecast.has("Temperature")) {
                temperature = dailyForecast.get("Temperature").get("Metric").get("Value");
            }
            WeatherResponse r = new WeatherResponse(date, weatherText.asText(), temperature.doubleValue());
            result.add(r);
        }

        return result;
    }

    public List<WeatherResponse> get5daysWeather(String cityKey) throws IOException {
        List<WeatherResponse> result = new ArrayList<>();

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
                .host(BASE_HOST)
                .addPathSegment(FORECAST_ENDPOINT)
                .addPathSegment(API_VERSION)
                .addPathSegment("daily")
                .addPathSegment("5day")
                .addPathSegment(cityKey)
                .addQueryParameter("apikey", API_KEY)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        String jsonResponse = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode dailyForecasts = objectMapper.readTree(jsonResponse).at("/DailyForecasts");
        for (JsonNode dailyForecast : dailyForecasts) {
            JsonNode date;
            JsonNode weatherText;
            JsonNode temperature = null;
            date = dailyForecast.get("Date");

                  if (dailyForecast.has("Day")) {
            weatherText = dailyForecast.get("Day").get("IconPhrase");

           } else {
                weatherText = dailyForecast.get("Night").get("IconPhrase");
            }
            if (dailyForecast.has("Temperature")) {
                temperature = dailyForecast.get("Temperature").get("Minimum").get("Value");
            }
            WeatherResponse r = new WeatherResponse(date.asText(), weatherText.asText(), temperature.doubleValue());
            result.add(r);
        }

        return result;
    }

    public String detectCityKey() throws IOException {
        String selectedCity = ApplicationGlobalState.getInstance().getSelectedCity();

        HttpUrl detectLocationURL = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment("locations")
                .addPathSegment(API_VERSION)
                .addPathSegment("cities")
                .addPathSegment("autocomplete")
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(detectLocationURL)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Невозможно прочесть информацию о городе. " +
                    "Код ответа сервера = " + response.code() + " тело ответа = " + response.body().string());
        }
        String jsonResponse = response.body().string();
        System.out.println("Произвожу поиск города " + selectedCity);

        if (objectMapper.readTree(jsonResponse).size() > 0) {
            cityName = objectMapper.readTree(jsonResponse).get(0).at("/LocalizedName").asText();
            countryName = objectMapper.readTree(jsonResponse).get(0).at("/Country/LocalizedName").asText();
            System.out.println("Найден город " + cityName + " в стране " + countryName);
        } else throw new IOException("Server returns 0 cities");

        return objectMapper.readTree(jsonResponse).get(0).at("/Key").asText();
    }
}