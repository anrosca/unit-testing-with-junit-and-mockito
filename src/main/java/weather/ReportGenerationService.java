package weather;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReportGenerationService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void generateReport(LocalDate date, ReportGenerationContext context) {
        if (isInvalidDate(date)) {
            throw new InvalidDateException();
        }
        context.generateReportFor(date);
    }

    public void generateMonthlyReport(ReportGenerationContext context) {
        LocalDate date = LocalDate.now();
        if (date.getDayOfMonth() == 1) {
            context.generateMonthlyReportFor(date.minusMonths(1));
        }
    }

    public void generateMonthlyReportAsync(ReportGenerationContext context) {
        LocalDate date = LocalDate.now();
        if (date.getDayOfMonth() == 1) {
            executorService.submit(() -> context.generateMonthlyReportFor(date.minusMonths(1)));
        }
    }

    private boolean isInvalidDate(LocalDate date) {
        return date == null;
    }

    public static class InvalidDateException extends RuntimeException {
    }

    public static void main(String[] args) {
        ReportGenerationService reportGenerationService = new ReportGenerationService();
        JasperReportGenerator generator = new JasperReportGenerator();
        WeatherStation weatherStation = new WeatherStation(new WeatherService());
        ReportGenerationContextImpl context =
                new ReportGenerationContextImpl(generator, weatherStation, "Chisinau");
        reportGenerationService.generateReport(LocalDate.now(), context);
    }
}
