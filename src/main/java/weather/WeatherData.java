package weather;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public double getFirstSubRangeMin() {
        return firstSubRangeMin;
    }

    public void setFirstSubRangeMin(double firstSubRangeMin) {
        this.firstSubRangeMin = firstSubRangeMin;
    }

    public double getFirstSubRangeMax() {
        return firstSubRangeMax;
    }

    public void setFirstSubRangeMax(double firstSubRangeMax) {
        this.firstSubRangeMax = firstSubRangeMax;
    }

    public double getSecondSubRangeMin() {
        return secondSubRangeMin;
    }

    public void setSecondSubRangeMin(double secondSubRangeMin) {
        this.secondSubRangeMin = secondSubRangeMin;
    }

    public double getSecondSubRangeMax() {
        return secondSubRangeMax;
    }

    public void setSecondSubRangeMax(double secondSubRangeMax) {
        this.secondSubRangeMax = secondSubRangeMax;
    }

    public double getThirdSubRangeMin() {
        return thirdSubRangeMin;
    }

    public void setThirdSubRangeMin(double thirdSubRangeMin) {
        this.thirdSubRangeMin = thirdSubRangeMin;
    }

    public double getThirdSubRangeMax() {
        return thirdSubRangeMax;
    }

    public void setThirdSubRangeMax(double thirdSubRangeMax) {
        this.thirdSubRangeMax = thirdSubRangeMax;
    }

    public static WeatherDataBuilder newBuilder() {
        return new WeatherDataBuilder();
    }

    public static final class WeatherDataBuilder {
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

        private WeatherDataBuilder() {
        }

        public WeatherDataBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public WeatherDataBuilder withValue(double value) {
            this.value = value;
            return this;
        }

        public WeatherDataBuilder withRangeMin(double rangeMin) {
            this.rangeMin = rangeMin;
            return this;
        }

        public WeatherDataBuilder withRangeMax(double rangeMax) {
            this.rangeMax = rangeMax;
            return this;
        }

        public WeatherDataBuilder withFirstSubRangeMin(double firstSubRangeMin) {
            this.firstSubRangeMin = firstSubRangeMin;
            return this;
        }

        public WeatherDataBuilder withFirstSubRangeMax(double firstSubRangeMax) {
            this.firstSubRangeMax = firstSubRangeMax;
            return this;
        }

        public WeatherDataBuilder withSecondSubRangeMin(double secondSubRangeMin) {
            this.secondSubRangeMin = secondSubRangeMin;
            return this;
        }

        public WeatherDataBuilder withSecondSubRangeMax(double secondSubRangeMax) {
            this.secondSubRangeMax = secondSubRangeMax;
            return this;
        }

        public WeatherDataBuilder withThirdSubRangeMin(double thirdSubRangeMin) {
            this.thirdSubRangeMin = thirdSubRangeMin;
            return this;
        }

        public WeatherDataBuilder withThirdSubRangeMax(double thirdSubRangeMax) {
            this.thirdSubRangeMax = thirdSubRangeMax;
            return this;
        }

        public WeatherData build() {
            WeatherData weatherData = new WeatherData();
            weatherData.setTitle(title);
            weatherData.setValue(value);
            weatherData.setRangeMin(rangeMin);
            weatherData.setRangeMax(rangeMax);
            weatherData.setFirstSubRangeMin(firstSubRangeMin);
            weatherData.setFirstSubRangeMax(firstSubRangeMax);
            weatherData.setSecondSubRangeMin(secondSubRangeMin);
            weatherData.setSecondSubRangeMax(secondSubRangeMax);
            weatherData.setThirdSubRangeMin(thirdSubRangeMin);
            weatherData.setThirdSubRangeMax(thirdSubRangeMax);
            return weatherData;
        }
    }
}
