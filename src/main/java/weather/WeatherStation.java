package weather;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class WeatherStation {
    private static final int MIN_TEMPERATURE = -40;
    private static final int MAX_TEMPERATURE = 50;
    private static final int ZERO = 0;
    private static final int FIFTEEN_DEGREES = 15;
    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    private final WeatherService weatherService;

    public List<WeatherData> getMeasurementsFor(String cityName, LocalDate date) {
        Temperature temperature = weatherService.getTemperatureFor(date, cityName);
        return Collections.singletonList(toWeatherData(temperature, makeTitle(cityName, date)));
    }

    public List<WeatherData> getMonthlyMeasurementsFor(String cityName, LocalDate date) {
        List<Temperature> temperaturesForMonth = weatherService.getTemperaturesForMonth(date, cityName);
        Temperature averageTemperature = calculateAverageTemperature(temperaturesForMonth);
        String makeMonthlyReportTitle = makeMonthlyReportTitle(cityName, date);
        return Collections.singletonList(toWeatherData(averageTemperature, makeMonthlyReportTitle));
    }

    private Temperature calculateAverageTemperature(List<Temperature> temperaturesForMonth) {
        double averageTemperature = temperaturesForMonth.stream()
                .mapToDouble(Temperature::getValue)
                .average()
                .orElse(0.0);
        return new Temperature(averageTemperature);
    }

    private WeatherData toWeatherData(Temperature temperature, String title) {
        return WeatherData.builder()
                .title(title)
                .value(temperature.getValue())
                .rangeMin(MIN_TEMPERATURE)
                .rangeMax(MAX_TEMPERATURE)
                .firstSubRangeMin(MIN_TEMPERATURE)
                .firstSubRangeMax(ZERO)
                .secondSubRangeMin(ZERO)
                .secondSubRangeMax(FIFTEEN_DEGREES)
                .thirdSubRangeMin(FIFTEEN_DEGREES)
                .thirdSubRangeMax(MAX_TEMPERATURE)
                .build();
    }

    private String makeTitle(String cityName, LocalDate date) {
        return String.format("%s - Temperature Report - %s", cityName, date);
    }

    private String makeMonthlyReportTitle(String cityName, LocalDate date) {
        return String.format("%s - Average Temperature Report For month - %s", cityName, YEAR_MONTH_FORMATTER.format(date));
    }
}
