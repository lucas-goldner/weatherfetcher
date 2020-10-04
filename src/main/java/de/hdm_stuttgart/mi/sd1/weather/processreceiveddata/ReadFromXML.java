package de.hdm_stuttgart.mi.sd1.weather.processreceiveddata;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadFromXML {
    /**
     * reads the downloaded weatherData.xml and returns the timestamps, temperatures and symbols from the XML file
     *
     * @param file the xmlfile weatherData.xml
     * @return an array of filled with weather information for further processing
     */
    WeatherInformation[] FetchXMLDataToSting(File file) {
        WeatherInformation[] weatherinformation = new WeatherInformation[0];
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            //Information is stored in "time" nodes
            NodeList times = doc.getElementsByTagName("time");
            weatherinformation = new WeatherInformation[times.getLength()];

            //seperator for splitting input and final results
            System.out.println("----------------------------");

            //iterate through nodes
            for (int i = 0; i < times.getLength(); i++) {
                Node time = times.item(i);
                NamedNodeMap timeAttributes = time.getAttributes();
                String timestamp = timeAttributes.getNamedItem("from").getNodeValue();
                String temperature = "";
                String symbol = "";

                NodeList children = time.getChildNodes();
                //iterate through children
                for (int k = 0; k < children.getLength(); k++) {
                    Node child = children.item(k);
                    if (child.getNodeName().equals("temperature")) { //pick temperatures
                        temperature = child.getAttributes().getNamedItem("value").getNodeValue();
                    }
                    if (child.getNodeName().equals("symbol")) { //pick symbol e.g. leichter Regen, sonnig etc..
                        symbol = child.getAttributes().getNamedItem("name").getNodeValue();
                    }
                }
                //store picked information in new object of WeatherInfo via constructor
                weatherinformation[i] = new WeatherInformation(timestamp, temperature, symbol);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherinformation;
    }
}

