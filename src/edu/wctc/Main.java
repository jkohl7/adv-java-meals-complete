package edu.wctc;

import java.io.*;
import java.util.*;

public class Main {

    private Scanner keyboard;
    private Cookbook cookbook;

    public Main() throws IOException {
        keyboard = new Scanner(System.in);
        cookbook = new Cookbook();

        FileInput indata = new FileInput("meals_data.csv");

        String line;

        System.out.println("Reading in meals information from file...");
        while ((line = indata.fileReadLine()) != null) {
            String[] fields = line.split(",");
            cookbook.addElementWithStrings(fields[0], fields[1], fields[2]);
        }

        runMenu();
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
    
    private void printMenu() {
        System.out.println("");
        System.out.println("Select Action");
        System.out.println("1. List All Items");
        System.out.println("2. List All Items by Meal");
        System.out.println("3. Search by Meal Name");
        System.out.println("4. Do Control Break");
        System.out.println("5. Exit");
        System.out.print("Please Enter your Choice: ");
    }

    private void runMenu() throws IOException {
        boolean userContinue = true;

        while (userContinue) {
            printMenu();

            String ans = keyboard.nextLine();
            switch (ans) {
                case "1":
                    cookbook.printAllMeals();
                    break;
                case "2":
                    listByMealType();
                    break;
                case "3":
                    searchByName();
                    break;
                case "4":
                    controlBreak();
                    break;
                case "5":
                    userContinue = false;
                    break;
            }
        }

        System.out.println("Goodbye");
        System.exit(0);
    }

    private void listByMealType() {
        // Default value pre-selected in case
        // something goes wrong w/user choice
        MealType mealType = MealType.DINNER;

        System.out.println("Which Meal Type");

        // Generate the menu using the ordinal value of the enum
        for (MealType m : MealType.values()) {
            System.out.println((m.ordinal() + 1) + ". " + m.getMeal());
        }

        System.out.print("Please Enter your Choice: ");
        String ans = keyboard.nextLine();

        try {
            int ansNum = Integer.parseInt(ans);
            if (ansNum < MealType.values().length) {
                mealType = MealType.values()[ansNum - 1];
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Meal Type " + ans + ", defaulted to " + mealType.getMeal() + ".");
        }

        cookbook.printMealsByType(mealType);
    }

    private void searchByName() {
        keyboard.nextLine();
        System.out.print("Please Enter Value: ");
        String ans = keyboard.nextLine();
        cookbook.printByNameSearch(ans);
    }





    private void controlBreak() throws IOException {
        //make the array list
        ArrayList<Meal> list = cookbook.getMeals();

        Math breakfast = new Math();
        Math lunch = new Math();
        Math dinner = new Math();
        Math dessert = new Math();

        //checks for the types of meals on the array list
        for(Meal m : list){
            try{

            if(m.getMealType().getMeal()=="Dessert"){
                dessert.addValue(m.getCalories());
            }
            else if(m.getMealType().getMeal()=="Dinner"){
                dinner.addValue(m.getCalories());
            }
            else if(m.getMealType().getMeal()=="Lunch"){
                lunch.addValue(m.getCalories());
            }
            else if(m.getMealType().getMeal()=="Breakfast"){
                breakfast.addValue(m.getCalories());
            }
            }catch(NullPointerException e){}

        }

        //displays the information found
        System.out.println("Meal Type - Total - Mean - Min - Max - Median");
        //breakfast
        System.out.println("Breakfast - "+breakfast.getTotal()+" - "+breakfast.getMean()+" - "+breakfast.getMin()+" - "+breakfast.getMax()+" - "+breakfast.getMedian());
        //lunch
        System.out.println("Lunch - "+lunch.getTotal()+" - "+lunch.getMean()+" - "+lunch.getMin()+" - "+lunch.getMax()+" - "+lunch.getMedian());
        //dinner
        System.out.println("Dinner - "+dinner.getTotal()+" - "+dinner.getMean()+" - "+dinner.getMin()+" - "+dinner.getMax()+" - "+dinner.getMedian());
        //Dessert
        System.out.println("Dessert - "+dessert.getTotal()+" - "+dessert.getMean()+" - "+dessert.getMin()+" - "+dessert.getMax()+" - "+dessert.getMedian());


    }

}