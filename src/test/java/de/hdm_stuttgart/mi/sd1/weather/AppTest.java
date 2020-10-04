package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.jsonreader.App;
import de.hdm_stuttgart.mi.sd1.weather.jsonreader.Searcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    Searcher searcher = new Searcher();
    App app = new App();
    @Test
    public void findsTheRightAmountOfCities(){
        Assertions.assertEquals(7, searcher.getCities("Stuttgart"));
        assertEquals(1, searcher.getCities("Köln"));
        assertEquals(10, searcher.getCities("Berlin"));
    }

    //Tests for correct Input while choosing the save or search function
    @Test
    public void correctInitialInput(){
        Assertions.assertEquals(1, app.catchMaliciousInput("1", 1));
        assertEquals(2, app.catchMaliciousInput("2", 1));
    }

    //If there is no City found the City name has to be wrong
    @Test
    public void correctCityName(){
        assertEquals(-1, searcher.getCities("adbiafbawbhfhbawhb"));
    }


}