package com.engeto;

public class PlantSettings {
    private static final String FILENAME = "kvetiny.txt";
    private static final int DEFAULT_FREQUENCY_OF_WATERING = 7;
    private static final String DELIMITER = "\t";
    private static final String OUTPUTNAME = "vystup.txt";

    public static String getFilename(){return FILENAME;}
    public static String getDelimiter(){return DELIMITER;}
    public static String outFilename(){return OUTPUTNAME;}
    public static int getDefaultFrequencyOfWatering(){return DEFAULT_FREQUENCY_OF_WATERING;}

}

// Priklad vyuzitia v kode:
// InputOutput.write(Settings.getFilename());