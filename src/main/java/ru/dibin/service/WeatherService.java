package ru.dibin.service;

import ru.dibin.persist.Weather;

import java.io.IOException;
import java.util.Date;

public interface WeatherService {

    Weather findByWeatherDate(Date weatherDate);

    Weather getInformationAboutTheCurrentWeather() throws IOException;
}
