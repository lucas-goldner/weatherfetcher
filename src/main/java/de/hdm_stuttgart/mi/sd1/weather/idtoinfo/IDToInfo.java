package de.hdm_stuttgart.mi.sd1.weather.idtoinfo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class IDToInfo {
    //String in 2 gecuttet und dazwischen wird dann die ID eingefügt
    private static String apiURL = "http://api.openweathermap.org/data/2.5/forecast?id=";
    private static String apiURL2 = "&units=metric&mode=xml&lang=de&appid=107dd92cf9a4f0bc04af7ba3ca6f9b3e";

    /**
     * This method put the id into two strings calls the API and receives the data
     *
     * @param id is the id of particular city that the user chooses
     * @throws IOException if resource not found
     */
    public static void getInfo(int id) throws IOException {
        String newURL = apiURL + id + apiURL2;
        System.out.println(newURL);
        FileUtils.copyURLToFile(new URL(newURL), new File("src/main/resources/weatherData.xml"));
        System.out.println("Downloaded");
    }
}