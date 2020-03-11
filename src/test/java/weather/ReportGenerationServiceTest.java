package weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReportGenerationServiceTest {

    public static final LocalDate CURRENT_DATE = LocalDate.of(2020, 5, 1);
    private ReportGenerationService reportGenerationService;

    @BeforeEach
    void setUp() {
        Supplier mock = mock(Supplier.class);
        Mockito.when(mock.get()).thenReturn(CURRENT_DATE);
//        Mockito.when(mock.get()).thenThrow(IllegalAccessException.class);
        reportGenerationService = new ReportGenerationService(mock) {
            @Override
            ExecutorService makeExecutorService() {
                ExecutorService mock = mock(ExecutorService.class);
                Mockito.doAnswer(invocationOnMock -> {
                    Runnable r = invocationOnMock.getArgument(0);
                    r.run();
                    return null;
                }).when(mock).submit(any(Runnable.class));
                return mock;
            }
        };
    }

    @Test
    public void testGenerateReport_withInvalidDate() {
        LocalDate date = null;
        ReportGenerationContext context = mock(ReportGenerationContext.class);

        assertThrows(ReportGenerationService.InvalidDateException.class, () -> {
            reportGenerationService.generateReport(date, context);
        });
    }

    @Test
    public void testGenerateMonthlyReport() {
        ReportGenerationContext context = mock(ReportGenerationContext.class);

        reportGenerationService.generateMonthlyReport(context);

//        assertEquals("M(2020-04-01)", context.getInvocations());
        Mockito.verify(context, times(1)).generateMonthlyReportFor(CURRENT_DATE.minusMonths(1));
    }

    @Test
    public void testGenerateMonthlyReport_mock() {
        ReportGenerationContext context = mock(ReportGenerationContext.class);

        reportGenerationService.generateMonthlyReport(context);

//        assertEquals("M(2020-04-01)", context.getInvocations());
        Mockito.verify(context).generateMonthlyReportFor(CURRENT_DATE.minusMonths(1));
    }

    @Test
    public void testGenerateMonthlyReportAsync() throws InterruptedException {
        ReportGenerationContext context = mock(ReportGenerationContext.class);

        reportGenerationService.generateMonthlyReportAsync(context);

//        assertEquals("M(2020-04-01)", context.getInvocations());
        Mockito.verify(context).generateMonthlyReportFor(CURRENT_DATE.minusMonths(1));
    }

}
