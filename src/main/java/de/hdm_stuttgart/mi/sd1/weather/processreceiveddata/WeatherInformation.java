package de.hdm_stuttgart.mi.sd1.weather.processreceiveddata;

public class WeatherInformation {
    private String timestamp;
    private String temperature;
    private String symbolData;

    public WeatherInformation(String timestamp, String temperature, String symbolData) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.symbolData = symbolData;
    }

    /**
     * returns the actual timestamp in the format yyyy-mm-ddThh:mm:ss
     *
     * @return yyyy-mm-ddThh:mm:ss as a String
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * returns the temperature in the format xx.xx°C
     *
     * @return the fetched temperature with °C at the end
     */
    public String getTemperature() {
        return temperature + "°C";
    }

    /**
     * returns the symboldata e.g. sunny, cloudy etc
     *
     * @return Sonnig, leicht bewölkt, regen... etc.
     */
    public String getSymbolData() {
        return symbolData;
    }

}
