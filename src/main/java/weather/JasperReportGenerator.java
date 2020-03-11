package weather;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

public class JasperReportGenerator {

    private static final String REPORT_TEMPLATE_PATH = "/reportTemplate.jasper";

    public byte[] generate(List<WeatherData> weatherData) {
        try {
            return tryGenerateReport(weatherData);
        } catch (JRException e) {
            throw new ReportGenerationException();
        }
    }

    private byte[] tryGenerateReport(List<WeatherData> weatherData) throws JRException {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(weatherData);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(REPORT_TEMPLATE_PATH));
        return fillAndExportReport(dataSource, jasperReport);
    }

    private byte[] fillAndExportReport(JRBeanCollectionDataSource dataSource, JasperReport jasperReport) throws JRException {
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        return outputStream.toByteArray();
    }

    public static class ReportGenerationException extends RuntimeException {
    }
}
