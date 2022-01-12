package ru.dibin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.dibin.service.WeatherService;

import java.util.Date;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public String getWeather(Model model) {
        LOGGER.info("Getting the current weather");

        Date dateNow = new Date();

        model.addAttribute("weather", weatherService.findByWeatherDate(dateNow));

        return "weather";
    }
}
