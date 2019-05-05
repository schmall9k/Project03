/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Order class that takes input from customers.

 */

package Simulation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

// Kiersten wrote uncommented code section. Elijah wrote the unused commented out section of code that may be needed later.

public class Order {

    public String order;

    public Order() {
        this.order = generateOrder();

    }

    public String generateOrder() {
        String order = "";
        Random rand = new Random();
        int typeIndex = rand.nextInt(2) ;
        if (typeIndex == 0) {
            Sandwich sandwich = new Roll();
            int numOfItemsSandwiches = rand.nextInt(5) + 1;
            for(int j = 0; j < numOfItemsSandwiches; j++) {
                int contentIndex = rand.nextInt(5);
                if(contentIndex == 0) {
                    sandwich = new Ham(sandwich);
                }
                if(contentIndex == 1) {
                    sandwich = new Turkey(sandwich);
                }
                if(contentIndex == 2) {
                    sandwich = new Mayonnaise(sandwich);
                }
                if(contentIndex == 3) {
                    sandwich = new Mustard(sandwich);
                }
                if(contentIndex == 4) {
                    sandwich = new Cheese(sandwich);
                }
            }
            double cost = sandwich.cost() + 1.25;
            order = sandwich.getDescription() + " | Price: $" + Math.round((cost + (cost * .1)) * 100.) / 100. + ", Time: " + (sandwich.seconds() + 95)/60 + "min";
        }
        else {
            Sandwich sandwich = new Wrap();
            int numOfItemsSandwiches = rand.nextInt(5) + 1;
            for(int j = 0; j < numOfItemsSandwiches; j++) {
                int contentIndex = rand.nextInt(5);
                if(contentIndex == 0) {
                    sandwich = new Ham(sandwich);
                }
                if(contentIndex == 1) {
                    sandwich = new Turkey(sandwich);
                }
                if(contentIndex == 2) {
                    sandwich = new Mayonnaise(sandwich);
                }
                if(contentIndex == 3) {
                    sandwich = new Mustard(sandwich);
                }
                if(contentIndex == 4) {
                    sandwich = new Cheese(sandwich);
                }

            }
            double cost = sandwich.cost() + 1.25;
            order = sandwich.getDescription() + " | Price: $" + Math.round((cost + (cost * .1)) * 100.) / 100. + ", Time: " + (sandwich.seconds() + 95)/60 + "min";
        }
        return order;
    }

    @Override
    public String toString()
    {
        return order;
    }

    // Following code was written but not needed
    // Elijah
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
