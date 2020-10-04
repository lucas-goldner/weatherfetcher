package de.hdm_stuttgart.mi.sd1.weather.processreceiveddata;

import java.io.File;

public class ProcessXMLtoOutput {
    /**
     * Formats the data of the given XML and prints it on the terminal
     *
     * @param xmlFileName the path where the xml is found. in this case just weatherData.xml
     */
    public void formatXMLToFormattedOutput(String xmlFileName) {
        ReadFromXML readFromXML = new ReadFromXML();
        WeatherInformation[] weatherInformation = readFromXML.FetchXMLDataToSting(new File(xmlFileName));
        FormatOutput output = new FormatOutput();
        output.processWeatherInformation(weatherInformation);
        return;
    }
}
