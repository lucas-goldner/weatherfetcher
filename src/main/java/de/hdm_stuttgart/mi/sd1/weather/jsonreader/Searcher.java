package de.hdm_stuttgart.mi.sd1.weather.jsonreader;

import org.json.simple.JSONObject;

/**
 * Searches the Cities.json for Cities containing the User Input
 */
public class Searcher {

    /**
     * Searches the Cities.json for Cities containing the User Input and saves them to an Array
     *
     * @param Input The user input to be searched
     * @return the number of Cities found
     */
    public int getCities(String Input) {
        int resultCounter = 0;
        int maxResults = 10;
        City newCity;
        App app = new App();
        app.setCitiesObj();
        for (int i = 0; i < 209579; i++)                                                 //iterates through all 209579 Cities in Cities.json
        {
            JSONObject currentCity = (JSONObject) app.getJa().get(i);                        //saves the current object to "currentCity"
            String currentCityName = (String) currentCity.get("name");                  //saves the current Cities Name to "currentCityName"

            if (currentCityName.contains(Input))                                         //if the current Cities Name contains the input it gets stored to an Object Array
            {
                long idAsInt = (long) currentCity.get("id");
                String nameAsString = (String) currentCity.get("name");
                newCity = new City(idAsInt, nameAsString);
                if (resultCounter <= maxResults - 1)                                      //after maximum results are reached
                {
                    City.cityArray = ExtendArray.extender(City.cityArray, newCity);
                    resultCounter++;                                                    //resultCounter keeps track of the amount of Cities found
                } else                                                                    //prevents searching the rest of Cities.json after maximum Results are reached
                {
                    return resultCounter;
                }


            } else {
            }


        }
        if (resultCounter > 0) {
            return resultCounter;
        }
        return -1;
    }

}
