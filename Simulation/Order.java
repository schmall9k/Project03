/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Order class that takes input from customers.

 */

package Simulation;
;
import java.util.Random;

// Kiersten wrote uncommented code section. Elijah wrote the unused commented out section of code that may be needed later.

public class Order {

    public String order;

    public Order() {
        this.order = generateOrder();

    }

    public Order(int orderNumber)
    {
        if (orderNumber == 1)
        {
            // multiple sandwiches. Lets do 3.
            // First sandwich: roll, ham, ham, cheese, lettuce.
            // Second : wrap, turkey, cheese, tomato, mayonnaise
            // Third: roll, mustard, ham, turkey, cheese, cheese
            Sandwich sandwich1 = new Roll();
            sandwich1 = new Ham(sandwich1);
            sandwich1 = new Ham(sandwich1);
            sandwich1 = new Cheese(sandwich1);
            sandwich1 = new Lettuce(sandwich1);

            Sandwich sandwich2 = new Wrap();
            sandwich2 = new Turkey(sandwich2);
            sandwich2 = new Cheese(sandwich2);
            sandwich2 = new Tomato(sandwich2);
            sandwich2 = new Mayonnaise(sandwich2);

            Sandwich sandwich3 = new Roll();
            sandwich3 = new Mustard(sandwich3);
            sandwich3 = new Ham(sandwich3);
            sandwich3 = new Turkey(sandwich3);
            sandwich3 = new Cheese(sandwich3);
            sandwich3 = new Cheese(sandwich3);

            double costS1 = sandwich1.cost() + 1.25;
            double costS2 = sandwich2.cost() + 1.25;
            double costS3 = sandwich3.cost() + 1.25;

            double cost = costS1 + costS2 + costS3;

            int time = sandwich1.seconds() + sandwich2.seconds() + sandwich3.seconds();

            order = "Sandwich 1: " + sandwich1.getDescription() +
                    "| Sandwich 2: " + sandwich2.getDescription() +
                    "| Sandwich 3: " + sandwich3.getDescription() +
                    " | Price: $" + Math.round((cost + (cost * .1)) * 100.) / 100. +
                    ", Time: " + (time + 95)/60 + "min";

        }
        else if (orderNumber == 2)
        {
            // second order will be only veges
            // tomato, tomato, lettuce, lettuce, mustard, mayo
            Sandwich sandwich = new Wrap();
            sandwich = new Tomato(sandwich);
            sandwich = new Tomato(sandwich);
            sandwich = new Lettuce(sandwich);
            sandwich = new Lettuce(sandwich);
            sandwich = new Mustard(sandwich);
            sandwich = new Mayonnaise(sandwich);

            double cost = sandwich.cost() + 1.25;
            order = sandwich.getDescription() +
                    " | Price: $" + Math.round((cost + (cost * .1)) * 100.) / 100. +
                    ", Time: " + (sandwich.seconds() + 95)/60 + "min";
        }
        else if (orderNumber == 3)
        {
            // something totally ridiculous.
            Sandwich sandwich = new Roll();
            sandwich = new Turkey(sandwich);
            sandwich = new Ham(sandwich);
            sandwich = new Turkey(sandwich);
            sandwich = new Ham(sandwich);
            sandwich = new Turkey(sandwich);
            sandwich = new Ham(sandwich);
            sandwich = new Lettuce(sandwich);
            sandwich = new Lettuce(sandwich);
            sandwich = new Tomato(sandwich);
            sandwich = new Cheese(sandwich);
            sandwich = new Cheese(sandwich);
            sandwich = new Cheese(sandwich);
            sandwich = new Mayonnaise(sandwich);
            sandwich = new Mustard(sandwich);
            double cost = sandwich.cost() + 1.25;
            order = sandwich.getDescription() +
                    " | Price: $" + Math.round((cost + (cost * .1)) * 100.) / 100. +
                    ", Time: " + (sandwich.seconds() + 95)/60 + "min";
        }
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
