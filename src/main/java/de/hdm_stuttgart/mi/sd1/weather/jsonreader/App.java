package de.hdm_stuttgart.mi.sd1.weather.jsonreader;

import java.io.*;

import de.hdm_stuttgart.mi.sd1.weather.processreceiveddata.ProcessXMLtoOutput;
import de.hdm_stuttgart.mi.sd1.weather.idtoinfo.IDToInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Scanner;

/**
 * The Main Application
 */
public class App {

    public static int Id;
    public Object citiesObj;
    public JSONArray ja;
    private SavedCityReader scr = new SavedCityReader();


    public void setCitiesObj() {
        this.citiesObj = null;
        JSONParser parser = new JSONParser();
        try {
            InputStreamReader ir = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Cities.json"));
            BufferedReader br = new BufferedReader(ir);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            Object obj = parser.parse(sb.toString());
            JSONArray arr = (JSONArray) obj;
            this.ja = arr;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object getCitiesObj() {
        return this.citiesObj;
    }

    public JSONArray getJa() {
        return this.ja;
    }

    //casts the parser into an Array


    /**
     * Receives the Initial User Input and shows both options.
     * @throws IOException
     */
    public void InitialInput() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("1.  Neue Stadt Suchen");
        System.out.println("2.  Gespeicherte Städte aufrufen");
        System.out.println("x   drücken zum verlassen");
        System.out.println("-----------------------------");
        System.out.print("Wählen sie eine Funktion:");
        int cleanInput = catchMaliciousInput(scan.nextLine(), 1);
        switch (cleanInput) {
            case 1:
                searchNewCity();
                break;
            case 2:
                this.scr.showSavedCities();
                System.out.print("Wählen sie eine Stadt:");
                cleanInput = catchMaliciousInput(scan.nextLine(), 1);
                JSONObject chosenCity = (JSONObject) this.scr.savedCities.get(cleanInput - 1);    //gets the chosen City from the JSON Array of the Save File
                long chosenId = (long) chosenCity.get("Id");                                            //gets the Chosencity's Id
                Id = (int) chosenId;
                if (Id == 0)                                                                             //empty save slots have as an Id
                {
                    System.out.println("Keine Stadt an Stelle " + '"' + (cleanInput - 1) + '"' + " gespeichert.");
                    InitialInput();
                }
                IDToInfo.getInfo(Id);                                                                   //hands the Id to the function calling the API
                ProcessXMLtoOutput processXMLtoOutput = new ProcessXMLtoOutput();
                processXMLtoOutput.formatXMLToFormattedOutput("src/main/resources/weatherData.xml");
                //Displays the output
                break;
            default:
                System.out.println("Invalide Eingabe");
                InitialInput();
                break;
        }

    }

    /**
     * Receives userinput for searching a city
     */
    private void searchNewCity() throws IOException {
        Scanner scan = new Scanner(System.in);
        Searcher searcher = new Searcher();
        System.out.print("Suchen sie nach einer Stadt: ");

        String inputCity = scan.nextLine();
        int cityCount = searcher.getCities(inputCity);                                                  //getCities searches for Cities, stores them in an Object Array and returns the number of found cities as an Int
        if (cityCount < 0) {
            System.out.println("Keine Städte die " + '"' + inputCity + '"' + " enthalten gefunden. ");
            InitialInput();

        } else {
            CitiesFound(cityCount, inputCity);
        }
    }

    /**
     * Displays found Cities and asks the user to choose one.
     *
     * @param cityCount Number of cities found in Cities.json
     * @param inputCity String of what the User searched for
     * @throws IOException
     */
    public void CitiesFound(int cityCount, String inputCity) throws IOException {                                       //Displays found Cities and lets the user choose one to display
        System.out.println(cityCount + " Städte gefunden die " + '"' + inputCity + '"' + " enthalten. Bitte Wählen sie eine.");
        System.out.println("-----------------------------");
        for (int i = 1; i < City.cityArray.length; i++) {
            System.out.println(i + ".  " + City.cityArray[i].getCityName());
        }          //Displays the found Cities
        System.out.println("-----------------------------");
        Scanner scan = new Scanner(System.in);
        int cleanInput = catchMaliciousInput(scan.nextLine(), 1);
        if (cleanInput >= 1 && cleanInput <= City.cityArray.length - 1) {
            Id = (int) City.cityArray[cleanInput].getCityId();                                                                       //gets the chosen City's Id from the City Array
            String chosenCityName = City.cityArray[cleanInput].getCityName();                                                        //gets the Name from the City Array
            IDToInfo.getInfo(Id);
            ProcessXMLtoOutput processXMLtoOutput = new ProcessXMLtoOutput();
            processXMLtoOutput.formatXMLToFormattedOutput("src/main/resources/weatherData.xml");                                       //displays the Output
            saveOrDiscard(Id, chosenCityName);
        } else {
            System.out.println("Invalide Auswahl");
            CitiesFound(cityCount, inputCity);

        }
    }

    /**
     * Lets the User choose to save or discard the city
     *
     * @param idToSave   Id of the chosen city as an Int
     * @param nameToSave Name of the chosen city
     */
    private void saveOrDiscard(int idToSave, String nameToSave) throws IOException {                                    //either saves the city or goes back to main menu
        Scanner scan = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("1.  Zurück zum Hauptmenü");
        System.out.println("2.  Stadt Speichern");
        System.out.println("x   drücken zum Verlassen");
        System.out.println("-----------------------------");
        int cleanInput = catchMaliciousInput(scan.nextLine(), 1);
        switch (cleanInput) {
            case 1:
                City.cityArray = City.cleanCityArray(City.cityArray);
                InitialInput();                                                                                                 //returns to main menu
                break;
            case 2:
                this.scr.showSavedCities();                                                                              //displays saved cities
                System.out.print("Wählen sie einen Speicherplatz:");
                cleanInput = catchMaliciousInput(scan.nextLine(), 1);
                this.scr.saveCity(cleanInput, nameToSave, idToSave);                                                     //saves city to Saves.json
                break;
            default:
                System.out.println("Invalide Eingabe");
                saveOrDiscard(idToSave, nameToSave);
                break;
        }
    }

    /**
     * Turns every Input into an int and returns values outside the boundary for false inputs
     *
     * @param Input          User Input as a String
     * @param lowestAccepted lower boundary of acceptable user inputs to not accidentally return a valid value from a false Input
     * @return Always returns an Integer
     */
    public int catchMaliciousInput(String Input, int lowestAccepted)                                                       //catches malicious Input and always returns an integer
    {
        if(Input.equals("x")){
            System.exit(0);
    }
        try {
            int inputInt = Integer.parseInt(Input);
            return inputInt;

        } catch (NumberFormatException e) {
            return lowestAccepted - 1;

        }

    }

}