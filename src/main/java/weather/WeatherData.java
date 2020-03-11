package weather;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class WeatherData {
    private String title;
    private double value;
    private double rangeMin;
    private double rangeMax;
    private double firstSubRangeMin;
    private double firstSubRangeMax;
    private double secondSubRangeMin;
    private double secondSubRangeMax;
    private double thirdSubRangeMin;
    private double thirdSubRangeMax;
}
