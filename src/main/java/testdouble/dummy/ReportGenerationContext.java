package testdouble.dummy;

import java.time.LocalDate;

public interface ReportGenerationContext {
    void generateReportFor(LocalDate date);

    WeatherStation getWeatherStation();

    String getCityName();
}
