package de.hdm_stuttgart.mi.sd1.weather;

import de.hdm_stuttgart.mi.sd1.weather.processreceiveddata.FormatOutput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFormatOutput {

    private static final String TIMESTAMP = "2020-06-28T12:00:00";
    FormatOutput formatOutput = new FormatOutput();
    @Test
    public void TestGetWeekDay(){
        assertEquals("Sonntag", formatOutput.getWeekDay(TIMESTAMP));
    }

    @Test
    public void TestFormatTime(){
        assertEquals("12:00 Uhr",formatOutput.formatTime(TIMESTAMP));
    }

    @Test
    public void TestFormatDate(){
        assertEquals("28.06.2020", formatOutput.formatDate(TIMESTAMP));
    }
}
