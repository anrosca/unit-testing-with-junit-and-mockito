package testdouble.dummy;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ReportGenerationService {

    public void generateReport(LocalDate date, ReportGenerationContext context) {
        if (isInvalidDate(date)) {
            throw new InvalidDateException();
        }
        context.generateReportFor(date);
    }

    public void generateMonthlyReports(ReportGenerationContext context) {
        LocalDate date = LocalDate.now().minusMonths(1);
        if (date.getDayOfMonth() == 1) {
            for (int day = 1; day <= date.lengthOfMonth(); ++day) {
                generateReport(date.withDayOfMonth(day), context);
            }
        }
    }

    public void generateMonthlyReportsAsync(ReportGenerationContext context) {
        LocalDate date = LocalDate.now().minusMonths(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        if (date.getDayOfMonth() == 1) {
            IntStream.rangeClosed(1, date.lengthOfMonth()).forEach(day -> {
                executorService.submit(() -> generateReport(date.withDayOfMonth(day), context));
            });
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
        WeatherStation weatherStation = new WeatherStation();
        ReportGenerationContextImpl context = new ReportGenerationContextImpl(generator, weatherStation, "Chisinau");
        reportGenerationService.generateReport(LocalDate.now(), context);
    }
}
