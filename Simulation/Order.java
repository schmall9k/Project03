/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Order class that takes input from customers.

 */

package Simulation;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Order {

    public String sandwich;
    public String chips;
    public String drink;

    List<String> sandwichOptions = Arrays.asList("Sandwich 1", "Sandwich 2", "Sandwich 3");
    List<String> chipOptions = Arrays.asList("Chips 1", "Chips 2");
    List<String> drinkOptions = Arrays.asList("Drink 1", "Drink 2", "Drink 3");

    public Order()
    {
        Random rand = new Random();
        this.sandwich = sandwichOptions.get(rand.nextInt(sandwichOptions.size()));
        this.chips = chipOptions.get(rand.nextInt(chipOptions.size()));
        this.drink = drinkOptions.get(rand.nextInt(drinkOptions.size()));
    }

    @Override
    public String toString()
    {
        return "Order: " + sandwich + "," + chips + "," + drink;
    }

    // Following code was written but not needed
    /*
    public void addOrder() {
        System.out.print("What is your order?");
        Scanner order= new Scanner(System.in);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Orders.txt"));
            out.write(String.valueOf(order));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editOrder(String lineToEdit, String newOrder) throws IOException {
        File inputFile = new File("Order.txt");
        File tempFile = new File("TempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;

        while((currentLine = reader.readLine()) != null) {

            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToEdit)) continue;
            writer.write(currentLine + System.getProperty(newOrder));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }


    public void deleteOrder(String lineToRemove ) throws IOException {
        File inputFile = new File("Order.txt");
        File tempFile = new File("TempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        
        String currentLine;

        while((currentLine = reader.readLine()) != null) {

            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }
        writer.close();
        reader.close();
        tempFile.renameTo(inputFile);
    }*/
}
