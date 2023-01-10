package com.engeto.zadania;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPurchases {
    final List<Purchase> purchaseList = new ArrayList<>();

    public static RegisterOfPurchases importFromFile(String filename,String delimiter) throws PlantException {
        String nextLine = "";
        // kontrolny vypis: System.out.println(nextLine);
        String[] items = new String[0];
        LocalDate date;
        BigDecimal price;
        Category category;
        String description;
        int lineNumber = 0;

        RegisterOfPurchases result = new RegisterOfPurchases();
        //nacitat data suboru

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) { //tým,že je to vsetko uvedene za try sa zabezpeci to, ze scanner sa po vyhodeni vynimky uzavrie znaci sa to ako try-with-resources...automaticky uzavrie otvorene zdroje,subory, sietove spojenie.
            //zpracuj otvoreny subor
            while (scanner.hasNextLine()) {
                lineNumber++;
                //zpracuj dalsi riadok
                nextLine = scanner.nextLine();
                // kontrolny vypis: System.out.println(nextLine);
                items = nextLine.split(delimiter);
                date = LocalDate.parse(items[0]);
                price = new BigDecimal(items[1]);
                category = Category.valueOf(items[2]);
                description = items[3];
                result.addPurchase(new Purchase(description, category, price, date));
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
                        "Spatne zadane datum"+items[0]+"na riadku cislo :"+lineNumber+":"+nextLine+"\n"+e.getLocalizedMessage());


        } catch (FileNotFoundException e) {
            throw new PlantException(
                    "Subor"+filename+"nebol najdeny"+e.getLocalizedMessage());
        }


        return result;
    }

    public void exportToFile(String filename,String delimiter){
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Purchase purchase : purchaseList)
            {
            writer.println(purchase.getPurchaseDate()+delimiter+purchase.getPrice()+delimiter+purchase.getCategory()+delimiter+purchase.getDescription());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
    }

    public void removePurchase(Purchase purchase){
        purchaseList.remove(purchase);
    }


    public List<Purchase> purchaseAfter(LocalDate limit){
        List<Purchase> result = new ArrayList<>();
        for (Purchase purchase : purchaseList){
            if (purchase.getPurchaseDate().isAfter(limit)){
                result.add(purchase);
            }
        }
        return result;
    }

    public BigDecimal totalPrice(){
        BigDecimal result = BigDecimal.ZERO;
        for (Purchase purchase: purchaseList){
            result = result.add(purchase.getPrice());
        }
        return result;
    }
    public int numberOfPurchases(){
        return purchaseList.size();
    }
    public BigDecimal averagePrice() throws PlantException {
        if(numberOfPurchases() <0) throw new RuntimeException("Pocet nakupu zaporny");
        if(numberOfPurchases() ==0 ) {
            //return BigDecimal.ZERO;
            throw new PlantException("Neide pocitat priemer - nie su data");
        }
        return totalPrice().divide(
                BigDecimal.valueOf(numberOfPurchases()), RoundingMode.HALF_UP);
    }




}
