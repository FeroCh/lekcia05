package com.engeto.plant;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;

public class Plant {
    static final int DEFAULT_FREQUENCY_WATERING = 7;
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name) {
        this(name,DEFAULT_FREQUENCY_WATERING,LocalDate.now());
    }

    public Plant(String name, int frequencyOfWatering, LocalDate planted) {
        this(name,"",planted,LocalDate.now(),frequencyOfWatering);
    }

    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        try {
            if (watering.isAfter(planted)) { this.watering = watering;
            }
        }catch (DateTimeException e){throw new PlantException("Datum zalievky nesmie byt starsi ako datum vysadby" + planted);}

    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        try{
            if (frequencyOfWatering >0){this.frequencyOfWatering = frequencyOfWatering;}
        } catch (IllegalArgumentException e){
            throw new PlantException("Frekvenica zalievky: "+frequencyOfWatering+"musi byt viac ako 1 den"+e.getLocalizedMessage());
        }
    }

    public String getWateringInfo(){
        return ("Meno rastliny: "+name
                +"Datum poslednej zalievky: "+watering
                +"Datum poslednej zalievky"+getWatering().plusDays(7));
    }












}
