package com.engeto.plant;

public class PlantSettings {
    private static final String FILENAME = "kvetiny.txt";
    private static final int DEFAULT_FREQUENCY_OF_WATERING = 7;
    private static final String DELIMITER = "\t";

    public static String getFilename(){return FILENAME;}
    public static int getDefaultFrequencyOfWatering(){return DEFAULT_FREQUENCY_OF_WATERING;}
    public static String getDelimiter(){return DELIMITER;}
}
// Priklad vyuzitia v kode:
// InputOutput.write(Settings.getFilename());