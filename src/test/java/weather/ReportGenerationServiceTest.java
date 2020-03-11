package weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ReportGenerationServiceTest {

    private ReportGenerationService reportGenerationService;

    @BeforeEach
    void setUp() {
        reportGenerationService = new ReportGenerationService();
    }

    @Test
    public void testGenerateReport_withInvalidDate() {
        LocalDate date = null;
        ReportGenerationContext context = null;

        assertThrows(ReportGenerationService.InvalidDateException.class, () -> {
            reportGenerationService.generateReport(date, context);
        });
    }
}
