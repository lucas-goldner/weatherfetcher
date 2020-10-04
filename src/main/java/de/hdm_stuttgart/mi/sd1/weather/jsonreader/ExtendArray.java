package de.hdm_stuttgart.mi.sd1.weather.jsonreader;

/**
 * Can extend arrays
 */
public class ExtendArray {

    public static final String DEFAULTSAVESTRING = "{\"Cities\":[{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"leer\"}," +
            "{\"Name\":\"Leer\",\"Id\":0}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}," +
            "{\"Id\":0,\"Name\":\"Leer\"}]}";

    /**
     *
     * @param Array the Array to be extended
     * @param Addition the City to add to the Array
     * @return Can extend a City array by an additional City
     */
    public static City[] extender(City Array[], City Addition)         //extends the Array storing the Cities found by searching the Citites.json
    {
        City[] copy = new City[Array.length + 1];
        for (int i = 0; i < Array.length; i++) {
            copy[i] = Array[i];
        }
        copy[Array.length] = Addition;
        return copy;
    }

}
