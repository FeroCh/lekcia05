package com.engeto.zadania;

public class PlantException extends Exception {
    //normalna trieda - toto dolu je vlastne konstruktor triedy Exception cez message odovzdavam hlasenie triede Exception. Lekcia 8 dedenie
    public PlantException(String message) {
        super(message);
    }
}
