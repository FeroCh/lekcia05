package com.engeto.plant;

import com.engeto.zadania.PlantException;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    final List<Plant> plantList = new ArrayList<>();
    int index = -1;
    int rozsah = 0;

//    private boolean isNumeric(int index) throws PlantException {
//        try {
//            return isNumeric(index);
//        }catch (NumberFormatException e){
//            throw new PlantException("Zadane hodnota"+index +"musi byt cislo"+e.getLocalizedMessage());
//        }
//    }
    public void addPlantList(Plant plant) {
        plantList.add(plant);
    }

    public void removePlantList(Plant plant) {
        plantList.remove(plant);
    }

    public Plant getPlantListIndex(Plant plant) throws Exception {
        rozsah = plantList.size() -1;
        System.out.println("Zadej index kvetiny v rozsahu 0 -"+rozsah+": \n");
        try(Scanner scanner = new Scanner(System.in)){
            while( (index < 0 || index > rozsah) ){ //||(!isNumeric(index))
            index = scanner.nextInt();}
        } catch (NumberFormatException e){
            throw new com.engeto.plant.PlantException("Zadane cislo"+index +"nesmie byt zaporne"+e.getLocalizedMessage());
        } catch (IllegalArgumentException e){
            throw new com.engeto.plant.PlantException("Zadany znak: "+index+"musi byt cislo >= 0! a mensie ako:"+rozsah);
        }
        return  plantList.get(index);
    }
//https://www.programiz.com/java-programming/examples/check-string-numeric
//https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java
//https://www.delftstack.com/howto/java/how-to-check-if-a-string-is-a-number-in-java/




//upravit pre PlantList
    public static PlantList importFromFile(String getFilename, String getDelimiter) throws PlantException{
        String nextLine = "";
        // kontrolny vypis: System.out.println(nextLine);
        String[] items = new String[1];
        String name;
        String notes;
        LocalDate planted;
        LocalDate watering;
        int frequencyOfWatering;
        int lineNumber = 0;

        PlantList result = new PlantList();
        //nacitat data suboru

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(getFilename)))) { //tým,že je to vsetko uvedene za try sa zabezpeci to, ze scanner sa po vyhodeni vynimky uzavrie znaci sa to ako try-with-resources...automaticky uzavrie otvorene zdroje,subory, sietove spojenie.
            //zpracuj otvoreny subor
            while (scanner.hasNextLine()) {
                lineNumber++;
                //zpracuj dalsi riadok
                nextLine = scanner.nextLine();
                // kontrolny vypis: System.out.println(nextLine);
                items = nextLine.split(getDelimiter);
                name = items[0];
                notes = items[1];
                planted = LocalDate.parse(items[2]);
                watering = LocalDate.parse(items[3]);
                frequencyOfWatering = Integer.parseInt(items[4]);
                result.addPlantList(new Plant(name,notes,planted,watering,frequencyOfWatering));
            }

            // NumberFormatException je potomok IllegalArgumentException a preto musi byt pred Illegal, inac by to zachytila IllegalArgumentException
        } catch (NumberFormatException e){
            throw new PlantException(
                    "Spatne zadane cislo"+items[1]+"na riadku cislo :"+lineNumber+":"+nextLine+"\n"+e.getLocalizedMessage());

        }  catch (IllegalArgumentException e){
            throw new PlantException(
                    "Spatne zadane kategoria"+items[2]+"na riadku cislo :"+lineNumber+":"+nextLine+"\n"+e.getLocalizedMessage());

        }  catch (DateTimeException e){
            throw new PlantException(
                    "Spatne zadane datum"+items[2]+"alebo:"+items[3]+"na riadku cislo :"+lineNumber+":"+nextLine+"\n"+e.getLocalizedMessage());


        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor"+getFilename+"nebol najdeny"+e.getLocalizedMessage());
        }


        return result;
    }
    public void exportToFile(String getFilename,String getDelimiter){
        try (PrintWriter writer = new PrintWriter(new FileWriter(getFilename))) {
            for (Plant plant : plantList)
            {
                writer.println(plant.getName()+getDelimiter+plant.getNotes()+getDelimiter+plant.getPlanted()+getDelimiter+plant.getWatering()+plant.getWateringInfo());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


