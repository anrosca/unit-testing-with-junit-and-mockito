package weather;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryReportRepositoryFake implements ReportRepository {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
    private static final Map<Long, Report> REPORTS = new HashMap<>();

    @Override
    public List<Report> findAll() {
        return new ArrayList<>(REPORTS.values());
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.of(REPORTS.get(id));
    }

    @Override
    public Report save(Report report) {
        report.setId(ID_GENERATOR.incrementAndGet());
        REPORTS.put(report.getId(), report);
        return report;
    }

    @Override
    public void deleteById(Long id) {
        REPORTS.remove(id);
    }
}
