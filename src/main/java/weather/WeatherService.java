package weather;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WeatherService {

    private static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    public Temperature getTemperatureFor(LocalDate date, String cityName) {
        try {
            return tryGetTemperatureFor(date, cityName);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Temperature> getTemperaturesForMonth(LocalDate date, String cityName) {
        try {
            return tryGetTemperaturesForMonth(date, cityName);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Temperature tryGetTemperatureFor(LocalDate date, String cityName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(makeDailyWeatherURI(date, cityName)).GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonBody = response.body();
        return JsonUtil.fromJson(jsonBody, Temperature.class);
    }

    private URI makeDailyWeatherURI(LocalDate date, String cityName) {
        return URI.create(String.format("http://localhost:8080/weather/%s/%s", cityName, date));
    }

    private List<Temperature> tryGetTemperaturesForMonth(LocalDate date, String cityName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(makeMonthlyWeatherURI(date, cityName)).GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String jsonBody = response.body();
        return JsonUtil.fromJson(jsonBody, new TypeReference<>() {
        });
    }

    private URI makeMonthlyWeatherURI(LocalDate date, String cityName) {
        return URI.create(String.format("http://localhost:8080/weather/%s/%s", cityName, YEAR_MONTH_FORMATTER.format(date)));
    }
}
