package de.hdm_stuttgart.mi.sd1.weather.processreceiveddata;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class FormatOutput {
    /**
     * processes an Array filled with weatherinformation and print its data on the console
     *
     * @param information Array with weather informationgit status
     */
    public void processWeatherInformation(WeatherInformation[] information) {
        String output = "";
        String lastWeekDay = "";
        for (int i = 0; i < information.length; i++) {
            String timestamp = information[i].getTimestamp();
            String tmpWeekday = getWeekDay(timestamp);

            if (!tmpWeekday.equals(lastWeekDay)) { //avoid repetitive print of weekday for better readability
                if (i != 0) { //seperate different days with one line break
                    System.out.println();
                }
                System.out.println(tmpWeekday + ", " + formatDate(timestamp));
            }
            lastWeekDay = tmpWeekday;
            //Output e.g. 00:00 Uhr:	17.99°C, Klarer Himmel
            output = "\t" + formatTime(timestamp) + ":\t"
                    + information[i].getTemperature()
                    + ", " + information[i].getSymbolData();
            System.out.println(output);
        }
        return;
    }

    /**
     * formats the Date input from yyyy-mm-ddThh:mm:ss to Montag, Dienstag...
     *
     * @param date String in yyyy-mm-ddThh:mm:ss format
     * @return Montag, Dienstag,..., Sonntag
     */
    public String getWeekDay(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDate localDate = LocalDate.parse(date, formatter); // LocalDate = 2010-02-23
        DayOfWeek dow = localDate.getDayOfWeek();  // Extracts a `DayOfWeek` enum object.
        return dow.getDisplayName(TextStyle.FULL, Locale.GERMAN); // String = Tue
    }

    /**
     * converts ISO LOCAL DATE TIME yyyy-mm-ddThh:mm:ss to dd:mm:yyyy
     *
     * @param date String in format yyyy-mm-ddThh:mm:ss
     * @return Date in format dd:mm:yyyy
     */
    public String formatDate(String date) {
        String year = date.substring(0, 4);// (yyyy)-mm-ddThh:mm:ss
        String month = date.substring(5, 7);// yyyy-(mm)-ddThh:mm:ss
        String day = date.substring(8, 10);// yyyy-mm-(dd)Thh:mm:ss
        return day + "." + month + "." + year;
    }

    /**
     * converts ISO LOCAL DATE TIME yyyy-mm-ddThh:mm:ss hh:mm Uhr
     *
     * @param date String in format yyyy-mm-ddThh:mm:ss
     * @return Time in format hh:mm Uhr
     */
    public String formatTime(String date) {
        String hour = date.substring(11, 13);
        String minute = date.substring(14, 16);
        return hour + ":" + minute + " Uhr";
    }
}
