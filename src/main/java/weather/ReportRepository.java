package weather;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {

    List<Report> findAll();

    Optional<Report> findById(Long id);

    Report save(Report report);

    void deleteById(Long id);
}
