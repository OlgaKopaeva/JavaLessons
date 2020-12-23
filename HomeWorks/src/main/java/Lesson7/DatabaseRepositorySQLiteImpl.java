package Lesson7;

import Lesson7.entity.WeatherData;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class DatabaseRepositorySQLiteImpl implements DatabaseRepository {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    String filename = null;
    String createTableQuery = "CREATE TABLE IF NOT EXISTS weather (\n" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            " city TEXT NOT NULL,\n" +
            " date_time TEXT NOT NULL,\n" +
            " weather_text TEXT NOT NULL,\n" +
            " temperature REAL NOT NULL\n" +
            ");";
    String insertWeatherQuery = "INSERT INTO weather (city, date_time, weather_text, temperature) VALUES (?,?,?,?)";
    String selectAllDataQuery = "SELECT * FROM weather ";

    public DatabaseRepositorySQLiteImpl() {
        filename = ApplicationGlobalState.getInstance().getDbFileName();
    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + filename);
        return connection;
    }

    private void createTableIfNotExists() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute(createTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean saveWeatherData(WeatherData weatherData) throws SQLException {
        createTableIfNotExists();
        try (Connection connection = getConnection();
             PreparedStatement saveWeather = connection.prepareStatement(insertWeatherQuery)) {
            saveWeather.setString(1, weatherData.getCity());
            saveWeather.setString(2, weatherData.getLocalDate());
            saveWeather.setString(3, weatherData.getText());
            saveWeather.setDouble(4, weatherData.getTemperature());
            saveWeather.close();
            connection.close();
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        throw new SQLException("Failure on saving weather object");
    }

    @Override
    public void getAllSavedData() throws IOException, SQLException {
        try (Connection connection = getConnection();
             Statement getSavedData = connection.createStatement();
        ) {
            ResultSet resultSet = getSavedData.executeQuery(selectAllDataQuery);
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt(1) + "|" +
                                resultSet.getString(2) + "|" +
                                resultSet.getString(3) + "|" +
                                resultSet.getDouble(4) + "|");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

