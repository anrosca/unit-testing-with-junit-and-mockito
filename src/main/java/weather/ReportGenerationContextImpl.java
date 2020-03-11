package weather;

import lombok.RequiredArgsConstructor;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class ReportGenerationContextImpl implements ReportGenerationContext {
    private static final String REPORT_FILE_NAME_PREFIX = "reports/";
    private static final String REPORT_FILE_NAME_SUFFIX = "_temperature.pdf";

    private final JasperReportGenerator reportGenerator;
    private final WeatherStation weatherStation;
    private final String cityName;

    @Override
    public void generateReportFor(LocalDate date) {
        try {
            List<WeatherData> measurements = weatherStation.getMeasurementsFor(cityName, date);
            byte[] reportContent = reportGenerator.generate(measurements);
            Files.write(Paths.get(generateFileName(date)), reportContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateMonthlyReportFor(LocalDate date) {
        try {
            List<WeatherData> measurements = weatherStation.getMonthlyMeasurementsFor(cityName, date);
            byte[] reportContent = reportGenerator.generate(measurements);
            String fileName = generateFileName(date);
            Files.write(Paths.get(fileName), reportContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateFileName(LocalDate date) {
        return String.format("%s%s%s", REPORT_FILE_NAME_PREFIX, date, REPORT_FILE_NAME_SUFFIX);
    }
}
