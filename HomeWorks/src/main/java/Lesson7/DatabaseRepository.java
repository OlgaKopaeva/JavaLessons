package Lesson7;

import Lesson7.entity.WeatherData;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// Репозиторий для работы
public interface DatabaseRepository {

    boolean saveWeatherData(WeatherData weatherData) throws SQLException;

    void getAllSavedData() throws IOException, SQLException;
}

