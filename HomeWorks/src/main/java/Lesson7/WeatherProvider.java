package Lesson7;

import Lesson7.enums.Periods;

import java.io.IOException;
import java.sql.SQLException;

public interface WeatherProvider {

    void getWeather(Periods periods) throws IOException, SQLException;

    void getAllFromDb() throws IOException, SQLException;

}
