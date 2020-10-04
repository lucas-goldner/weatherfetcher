package de.hdm_stuttgart.mi.sd1.weather.jsonreader;

/**
 * City class to temporarily store found Cities in an Array
 */
public class City {
    public long cityId;
    private String cityName;
    public static City cityArray[] = new City[1];

    /**
     * Creator for a City object
     *
     * @param cityId   Id of the City
     * @param cityName Name of the City
     */
    public City(long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    /**
     * Get the city name
     * @return cityname
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Get the city Id
     * @return cityid
     */
    public long getCityId() {
        return cityId;
    }


    /**
     * Empties the City Array
     * @param cityArray the array that stores city information
     * @return The emptied Array
     */
    public static City[] cleanCityArray(City[] cityArray){
        cityArray = new City[1];
        return cityArray;
    }

}
