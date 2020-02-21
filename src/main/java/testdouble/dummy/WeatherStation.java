package testdouble.dummy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherStation {
    public List<WeatherData> getMeasurementsFor(String cityName, LocalDate date) {
        List<WeatherData> weatherData = new ArrayList<WeatherData>();
        weatherData.add(WeatherData.newBuilder()
                .withTitle(cityName + " - Temparature Report - " + date)
                .withValue(ThreadLocalRandom.current().nextDouble(-40, 50))
                .withRangeMin(-40)
                .withRangeMax(50)
                .withFirstSubRangeMin(-40)
                .withFirstSubRangeMax(0)
                .withSecondSubRangeMin(0)
                .withSecondSubRangeMax(15)
                .withThirdSubRangeMin(15)
                .withThirdSubRangeMax(50)
                .build());
        return weatherData;
    }
}
