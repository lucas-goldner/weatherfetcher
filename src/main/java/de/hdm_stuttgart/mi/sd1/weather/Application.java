package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.jsonreader.App;
import de.hdm_stuttgart.mi.sd1.weather.jsonreader.ExtendArray;

import java.io.*;

public class Application {

    /**
     * App Starting point
     *
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();
        try {
            if (new File("./SavedCities.json").exists()) {
            } else {
                File saveFile = new File("./SavedCities.json");
                FileWriter fw = new FileWriter(saveFile);
                fw.write(ExtendArray.DEFAULTSAVESTRING);
                fw.flush();
                fw.close();
            }
            app.InitialInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
