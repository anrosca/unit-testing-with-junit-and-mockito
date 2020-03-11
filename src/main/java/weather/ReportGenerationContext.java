package weather;

import java.time.LocalDate;

public interface ReportGenerationContext {
    void generateReportFor(LocalDate date);

    void generateMonthlyReportFor(LocalDate date);
}
