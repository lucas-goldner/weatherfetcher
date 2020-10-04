package de.hdm_stuttgart.mi.sd1.weather.jsonreader;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Reads and Writes in the Json containing the user-saved cities
 */
public class SavedCityReader {
    public Object savesObj;
    public JSONObject joRead;
    public JSONArray savedCities;                    //stores the "Cities" Array from Saves.json
    public JSONObject joWrite = new JSONObject();


    public void setSavesObj() {
        JSONParser parser = new JSONParser();
        try {
            //InputStreamReader ir2 = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Saves.json"));
            InputStreamReader ir = new InputStreamReader(new FileInputStream("SavedCities.json"));
            BufferedReader br = new BufferedReader(ir);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Object obj = parser.parse(sb.toString());
            this.savesObj = obj;
            this.joRead = (JSONObject) this.savesObj;
            this.savedCities = (JSONArray) joRead.get("Cities");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a hashmap for the City to be saved
     *
     * @param storageLocation Storage location of the City to be saved
     * @param Id              Id of the City to be saved
     * @param Name            Name of the City to be saved
     * @throws FileNotFoundException
     */
    public void saveCity(int storageLocation, String Name, int Id) throws FileNotFoundException {
        Map m = new LinkedHashMap(2);                                               //creates a hashmap with capacity 2
        m.put("Name", Name);                                                                     //stores Name and Id of the City in said hashmap
        m.put("Id", Id);
        savedCities.set(storageLocation - 1, m);                                                 //puts the City in the chosen saveslot
        joWrite.put("Cities", savedCities);
        writeIn("SavedCities.json", joWrite);
    }

    /**
     * Writes into Jason files
     *
     * @param fileName   Name of the File to be written in
     * @param JasonWrite the Object that gets written into the file
     * @throws FileNotFoundException
     */
    public void writeIn(String fileName, JSONObject JasonWrite) throws FileNotFoundException {
        try {
            FileWriter bwr = new FileWriter("SavedCities.json");
            bwr.write(JasonWrite.toJSONString());
            bwr.flush();
            bwr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the stored Cities
     */
    public void showSavedCities()                                                                //Displays the Saved Cities
    {
        setSavesObj();

        System.out.println("Gespeicherte Städte");
        System.out.println("-----------------------------");
        for (int i = 0; i < 10; i++) {
            JSONObject currentCity = (JSONObject) savedCities.get(i);
            System.out.println((i + 1) + ".  Name: " + currentCity.get("Name") + "  ID: " + currentCity.get("Id"));
        }
        System.out.println("-----------------------------");


    }
}

