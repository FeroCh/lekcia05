package com.engeto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Main {
    public static void main(String[] args) throws PlantException {
        PlantList plants = null;
        try {
            plants = PlantList.importFromFile(PlantSettings.getFilename(), PlantSettings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + PlantSettings.getFilename()
                    + ":\n" + e.getLocalizedMessage());
        }

        try {
            plants = PlantList.importFromFile("kvetiny-spatne-datum.txt", PlantSettings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + "kvetiny-spatne-datum.txt"
                    + ":\n" + e.getLocalizedMessage());
        }

        try {
            plants = PlantList.importFromFile("kvetiny-spatne-frekvence.txt", PlantSettings.getDelimiter());
        } catch (PlantException e) {
            System.err.println("Chyba při čtení souboru " + "kvetiny-spatne-frekvence.txt"
                    + ":\n" + e.getLocalizedMessage());
        }
        plants.addPlantList(new Plant("Amarila v obyvaku"));
        plants.addPlantList(new Plant("Bazalka v kuchyni", 3, LocalDate.now()));

//        for (Plant plant : plants.
//        ) {
//            System.out.println(plant);
//        }

        // osetreni tlace podla poziadavok.


        plants.exportToFile(PlantSettings.outFilename(),PlantSettings.getDelimiter());
//        plant.removePlantList(2);
//        PlantList listOfPlants = new PlantList();

//        List<Plant> listOfPlants = new ArrayList<>();
//        Set<Plant> setOfPlants = new TreeSet<>();
//        Plant hyacint = new Plant("Hyacint","",LocalDate.now().minusDays(20),LocalDate.now().minusDays(4),6);
//        Plant fikus = new Plant("Fikus","",LocalDate.now().minusDays(21),LocalDate.now().minusDays(6),7);
//        Plant gerbera = new Plant("Gerbera","",LocalDate.now().minusDays(19),LocalDate.now().minusDays(9),10);
//        listOfPlants.add(hyacint);
//        listOfPlants.add(fikus);
//        listOfPlants.add(gerbera);
//        Collections.sort(listOfPlants,
//                new Comparator<Plant>() {
//                    @Override
//                    public int compare(Plant o1, Plant o2){
//                        return o1.getName().compareTo(o2.getName());
//                        }
//                });
//        listOfPlants.forEach(plant1 -> System.out.print(plant1 +"\n"));
//        System.out.println("-------");
//        Collections.sort(listOfPlants,
//                new Comparator<Plant>() {
//                    @Override
//                    public int compare(Plant o1, Plant o2) {
//                        return o1.getWatering().compareTo(o2.getWatering());
//                    }
//                });
//        listOfPlants.forEach(plant1 -> System.out.print(plant1 +"\n"));
//        System.out.println("++++++++");
//        listOfPlants.forEach(plant1 -> System.out.print(plant1.getWatering() +"\n"));
//        System.out.println("========");
//        setOfPlants.addAll(listOfPlants);
//        setOfPlants.forEach(plant1 -> System.out.print(plant1 +"\n"));
//        LocalDate today = LocalDate.now();
//        listOfPlants.forEach(plant1 -> {if (ChronoUnit.DAYS.between(plant1.getWatering(), today) < 5)
//            System.out.println(plant1.getWatering());});
//
    }
}
