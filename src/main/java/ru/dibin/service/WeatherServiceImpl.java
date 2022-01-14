package ru.dibin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dibin.persist.Weather;
import ru.dibin.persist.WeatherRepository;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Weather findByWeatherDate(final Date weatherDate) {

        Weather weather = null;

        try {
            weather = weatherRepository
                    .findByDate(weatherDate)
                    .orElse(getInformationAboutTheCurrentWeather());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }

    @Transactional
    @Override
    public Weather getInformationAboutTheCurrentWeather() throws IOException {

        URL url = new URL("https://yandex.ru");
        URLConnection connection = url.openConnection();

        BufferedReader input =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder date = new StringBuilder();
        while ((inputLine = input.readLine()) != null) {
            date.append(inputLine);
        }

        int x = date.indexOf("<div class='weather__temp'>");

        Date dateNow = new Date();

        Weather weatherNew = new Weather(dateNow, date.substring(x + 27, x + 30));

        weatherRepository.save(weatherNew);

        return weatherNew;
    }
}