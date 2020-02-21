package testdouble.dummy;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class ReportGenerationContextImpl implements ReportGenerationContext {
    private static final String REPORT_FILE_NAME_PREFIX = "reports/";
    private static final String REPORT_FILE_NAME_SUFFIX = "_temperature.pdf";

    private final JasperReportGenerator reportGenerator;
    private final WeatherStation weatherStation;
    private final String cityName;

    public ReportGenerationContextImpl(JasperReportGenerator reportGenerator, WeatherStation weatherStation, String cityName) {
        this.reportGenerator = reportGenerator;
        this.weatherStation = weatherStation;
        this.cityName = cityName;
    }

    @Override
    public void generateReportFor(LocalDate date) {
        try {
            byte[] reportContent = reportGenerator.generate(weatherStation, cityName, date);
            Files.write(Paths.get(generateFileName(date)), reportContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WeatherStation getWeatherStation() {
        return weatherStation;
    }

    @Override
    public String getCityName() {
        return cityName;
    }

    private String generateFileName(LocalDate date) {
        return REPORT_FILE_NAME_PREFIX + date + REPORT_FILE_NAME_SUFFIX;
    }
}
