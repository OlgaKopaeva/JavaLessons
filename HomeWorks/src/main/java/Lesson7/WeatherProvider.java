package Lesson7;

import Lesson7.enums.Periods;
import Lesson7.entity.WeatherData;

import java.io.IOException;
import java.sql.SQLException;

public interface WeatherProvider {

    void getWeather(Periods periods) throws IOException, SQLException;

    WeatherData getAllFromDb() throws IOException;

}
