package com.engeto;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
    }
    public List<Plant> getPlants() {
        // Vrátíme kopii seznamu.
        // Pokud následně někdo v kopii provede změny, neovlivní tím
        //  náš seznam.
        return new ArrayList<>(getPlants());
    }
    static final List<Plant> plantList = new LinkedList<>();

    public void addPlantList(Plant plant) {
        plantList.add(plant);
    }

    public void removePlantList(int index) {
        plantList.remove(index);
    }

    public void  getPlantList(Plant index){plantList.indexOf(index);}

    public final int numberofPlantList(){return plantList.size();}

    public static PlantList importFromFile(String getFilename, String getDelimiter) throws PlantException{
        String nextLine = "";
        String[] items = new String [1];
        String name;
        String notes;
        LocalDate planted;
        LocalDate watering;
        int frequencyOfWatering;
        int lineNumber = 0;

        PlantList result = new PlantList();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(getFilename)))) {
            //tým,že je to vsetko uvedene za try sa zabezpeci to, ze scanner sa po vyhodeni
            // vynimky uzavrie znaci sa to ako try-with-resources...automaticky uzavrie otvorene
            // zdroje,subory, sietove spojenie.
            //zpracuj otvoreny subor
            while (scanner.hasNextLine()) {
                lineNumber++;
                //zpracuj dalsi riadok
                nextLine = scanner.nextLine();
                //kontrolny vypis: System.out.println(nextLine);
                items = nextLine.split(getDelimiter);
                name = items[0];
                notes = items[1];
                planted = LocalDate.parse(items[2]);
                watering = LocalDate.parse(items[3]);
                frequencyOfWatering = Integer.parseInt(items[4]);
                result.addPlantList(new Plant(name,notes,planted,watering,frequencyOfWatering));
            }
        } catch (NumberFormatException e){
            throw new PlantException(
                    "Spatne zadane cislo "+items[4]+" na riadku cislo : "+lineNumber+"\n"+e.getLocalizedMessage());

        } catch (DateTimeException e){
            throw new PlantException(
                    "Spatne zadane datum "+items[2]+"na riadku cislo :"+lineNumber+"\n"+e.getLocalizedMessage());

        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor"+getFilename+"nebol najdeny"+e.getLocalizedMessage());
        }
        return result;
    }

    public void exportToFile(String outFilename,String getDelimiter){
        try (PrintWriter writer = new PrintWriter(new FileWriter(outFilename))) {
            for (Plant plant : plantList)
            {
                writer.println(plant.getName()+getDelimiter+plant.getNotes()+getDelimiter+plant.getWatering()+getDelimiter+plant.getWateringInfo()+getDelimiter+plant.getFrequencyOfWatering());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}