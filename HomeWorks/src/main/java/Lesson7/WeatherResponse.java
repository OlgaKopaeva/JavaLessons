package Lesson7;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private String weatherText;

    public WeatherResponse() {
        super();
    }
    public WeatherResponse(String date, String weatherText, double temperature) {
        this.date = date;
        this.setWeatherText(weatherText);
        Temperature t = new Temperature();
        Temperature.Metric m = t.new Metric();
        m.setValue(temperature);
        t.setMetric(m);
        this.setTemperature(t);
    }

    @JsonGetter("Date")
    public String getDate() {
        return date;
    }

    @JsonGetter("WeatherText")
    public String getWeatherText() {
        return weatherText;
    }

    @JsonSetter("WeatherText")
    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Temperature {
        public Temperature() {
            super();
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Metric {
            public Metric() {
                super();
            }


            private double value;

            @JsonSetter("Value")
            public void setValue(double value) {
                this.value = value;
            }

            @JsonGetter("Value")
            public double getValue() {
                return value;
            }

        }

        public Metric metric;

        @JsonSetter("Metric")
        public void setMetric(Metric metric) {
            this.metric = metric;
        }
    }

    public String date;
    public Temperature temperature;

    @JsonSetter("Temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}

