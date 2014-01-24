package org.sonatype.mavenbook.weather;

import org.apache.log4j.PropertyConfigurator;
import org.sonatype.mavenbook.weather.model.Location;
import org.sonatype.mavenbook.weather.model.Weather;
import org.sonatype.mavenbook.weather.persist.LocationDAO;
import org.sonatype.mavenbook.weather.persist.WeatherDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    private WeatherService weatherService;
    private WeatherDAO weatherDAO;
    private LocationDAO locationDAO;

    public static void main(String[] args) throws Exception {
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));

        String zipcode = "60202";
        try {
            zipcode = args[0];
        } catch (Exception e) {}

        String operation = "weather";
        try {
            operation = args[1];
        } catch (Exception e) {}

        Main main = new Main(zipcode);

        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "classpath:applicationContext-weather.xml",
                "classpath:applicationContext-persist.xml"
        });

        main.weatherService = (WeatherService) context.getBean("weatherService");
        main.weatherDAO = (WeatherDAO) context.getBean("weatherDAO");
        main.locationDAO = (LocationDAO) context.getBean("locationDAO");

        if(operation.equals("weather")) {
            main.getWeather();
        } else {
            main.getHistory();
        }
    }

    private String zip;

    public Main(String zip) {
        this.zip = zip;
    }

    public void getWeather() throws Exception {
        Weather weather = weatherService.retrieveForecast(zip);
        weatherDAO.save(weather);
        System.out.print(new WeatherFormatter().formatWeather(weather));
    }

    public void getHistory() throws Exception {
        Location location = locationDAO.findByZip(zip);
        List<Weather> weathers = weatherDAO.recentForLocation(location);
        System.out.print(new WeatherFormatter().formatHistory(location, weathers));
    }
}
