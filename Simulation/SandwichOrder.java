package Simulation;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;

public class SandwichOrder {

    public static void main(String args[]) {

        //{"Ham", "Turkey", "Mayonnaise", "Mustard", "Cheese"} Just a note that I use for indexing - Ignore

        for(int i = 0; i < 100; i++ ) {
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
                System.out.print(sandwich.getDescription() + " | Price: $");
                System.out.format("%.2f",cost + (cost * .1));
                System.out.println(", Time: " + sandwich.seconds() + 95 + "sec");
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
                System.out.print(sandwich.getDescription() + " | Price: $");
                System.out.format("%.2f",cost + (cost * .1));
                System.out.println(", Time: " + sandwich.seconds() + 95 + "sec");
            }

        }

    }

}
