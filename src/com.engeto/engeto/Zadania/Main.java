package com.engeto.zadania;

import java.time.LocalDate;

public class Main {

    public static final String FILENAME = "purchase.csv";
    public static final String DELIMITER = ";";// "\t" tabulator k cviceniu
    public static final int STATUS_CANNOT_READ_FILE = 1;

    public static void main(String[] args) {
        RegisterOfPurchases purchases = null; //null slo by to nahradit aj new RegisterOfPurchase();
        try {
            purchases = RegisterOfPurchases.importFromFile(FILENAME,DELIMITER);
        } catch (PlantException e) {
            //e.printStackTrace();
            System.err.println("Chyba pri citani suboru\n"+FILENAME+":\n"+e.getLocalizedMessage());
            System.exit(STATUS_CANNOT_READ_FILE);
        }
        for (Purchase purchase: purchases.purchaseAfter(LocalDate.EPOCH)){
            System.out.println(purchase);
        }

        System.out.println("Celkova cena :"+purchases.totalPrice());
        try {
            System.out.println("Prumerna cena :"+purchases.averagePrice()+"");
        } catch (PlantException ex) {
            System.err.println("Neide spocitat priemernu cenu,kym nie su vlozene nakupy!\n"+ex.getLocalizedMessage()); //Spravnejsie riesenie, vyhoda, trieda RegisterOfPurchases to nemusi riesit.
// rychle riesenie            ex.printStackTrace();
        }
        purchases.exportToFile(FILENAME,DELIMITER);
    }
}
