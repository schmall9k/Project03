/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Main will run a simulation of the truck.

 */

package Simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Kylie wrote this main class simulator.

public class Main {

    public static void main(String[] args) throws IOException {
        // determine what route the truck will take (ask the user which they'd like to use)
        Route trucksRoute;
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Which route do you want the truck to take? (n for normal, r for right turns only): ");
        String answer = reader.nextLine();

        if (answer.equals("n")) {
            trucksRoute = new OriginalRoute();
            System.out.println("Normal route chosen.");
        }
        else if (answer.equals("r")) {
            trucksRoute = new SecondRoute();
            System.out.println("Right turn only route chosen.");
        }
        else {
            System.out.println("That's not a valid input... we'll just go with the normal route!");
            trucksRoute = new OriginalRoute();
        }
        System.out.println();

        // create Truck
        Truck truck = new Truck(Neighborhood.DIST_CENTER, trucksRoute);

        // declare a neighborhood. give the neighborhood a number of streets and its distribution center
        Neighborhood neighborhood = new Neighborhood();

        // create random addresses, write them to a file
        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();

        System.out.println("Which display type would you like? (g for GUI, m for Monitor, b for both): ");
        answer = reader.nextLine();


        if (answer.equals("g")) {
            // needed for GUI display
            NeighborhoodGUI map = new NeighborhoodGUI(truck, neighborhood);

            // gui display observer
            GUIDisplay guiDisplay = new GUIDisplay(map);
            truck.registerObserver(guiDisplay);
            System.out.println("GUI display chosen.");
        }
        else if (answer.equals("m")) {
            // monitor display observer
            MonitorDisplay monitorDisplay = new MonitorDisplay();
            truck.registerObserver(monitorDisplay);
            System.out.println("Right turn only route chosen.");
        }
        else if (answer.equals("b")){
            // needed for GUI display
            NeighborhoodGUI map = new NeighborhoodGUI(truck, neighborhood);

            // gui display observer
            GUIDisplay guiDisplay = new GUIDisplay(map);
            truck.registerObserver(guiDisplay);
            // monitor display observer
            MonitorDisplay monitorDisplay = new MonitorDisplay();
            truck.registerObserver(monitorDisplay);
            System.out.println("Both chosen.");
        }
        else {
            System.out.println("That's not a valid input... we'll just go with the GUI display!");
            // needed for GUI display
            NeighborhoodGUI map = new NeighborhoodGUI(truck, neighborhood);

            // gui display observer
            GUIDisplay guiDisplay = new GUIDisplay(map);
            truck.registerObserver(guiDisplay);
        }
        reader.close();

        // access the deliveries and determine truck's starting point
        ArrayList<Address> listOfDeliveries = neighborhood.getSortedDeliveries();
        ArrayList<Address> completedDeliveries = neighborhood.getCompletedDeliveries();
        Address start = truck.getCurrentLocation();

        System.out.print("The truck's route distance is: ");
        System.out.format("%.2f", neighborhood.calculateTrucksRouteDistance(truck));
        System.out.println(" miles.");

        double cost = 0;

        // runs through list of deliveries, calculates each route, and displays the simulation
        for (int i = 0; i < listOfDeliveries.size(); i++) {

            // calculate the route the truck will take
            truck.getRoute().calculateRoute(start, listOfDeliveries.get(i));

            cost += truck.getRoute().costEffectivenessOfRoute(trucksRoute.getListOfTruckLocations(),neighborhood.queueOfAddresses);

            // print out next delivery location
            System.out.println(listOfDeliveries.get(i));


            // pause before listing truck locations
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
            }

            // get the route (the locations that the truck will follow)
            ArrayList<Address> route = truck.getRoute().getListOfTruckLocations();

            // loop that will display the truck's movement
            for (int j = 0; j < route.size(); j++) {
                //NEW CODE
                truck.setCurrentLocation(route.get(j));
                truck.notifyObserver(route.get(j));
            }

            System.out.println("Order completed! Onto the next order... ");

            // pause between orders
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
            }

            // update truck's current location
            start = truck.getCurrentLocation();

            // mark an order as completed and make truck pause at house
            completedDeliveries.add(listOfDeliveries.get(i));
            neighborhood.setCompletedDeliveries(completedDeliveries);

            //clear list to determine route to next delivery
            truck.route.clearListOfLocations();
        }

        System.out.print("Cost efficiency in seconds = ");
        System.out.format("%.2f",cost);
        System.out.print("\n");
        System.out.println("Converted time cost (in hours:minutes:seconds) = " + truck.route.convertTime(cost));

    }
}

        /*
        Following code is for testing and comparing cost efficiency (time) and distance


        Address distCenter = new Address(510, "East", 5, "", "");
        Address address = new Address(510, "South", 5, "", "");

        truck.route.calculateRoute(distCenter,address);
        ArrayList<Address> route1 = truck.route.getListOfTruckLocations();
        int cost = truck.route.costEffectivenessOfRoute(route1, neighborhood.queueOfAddresses);
        int distance = distCenter.calculateDistanceFromLocation(address);

        for (int i = 0; i < route1.size(); i++)
        {
            System.out.println(route1.get(i).toString());
        }

        System.out.println(cost);
        System.out.println(distance);
        */




        /*
        USE THIS BLOCK OF CODE TO RUN SIMPLE SIMULATION TO TWO ADDRESSES

        ArrayList<Address> listOfDeliveries = new ArrayList<>();
        Address address1 = new Address(650, "East", 5, "", "");
        Address address2 = new Address(610, "South", 5, "", "");
        listOfDeliveries.add(address1);
        listOfDeliveries.add(address2);

    ArrayList<Address> completedDeliveries = neighborhood.getCompletedDeliveries();
    Address start = truck.getCurrentLocation();

    // runs through list of deliveries, calculates each route, and displays the simulation
        for (int i = 0; i < listOfDeliveries.size(); i++) {
        truck.getRoute().calculateRoute(start, listOfDeliveries.get(i));
        System.out.println(listOfDeliveries.get(i));
        ArrayList<Address> route = truck.getRoute().getListOfTruckLocations();

        // loop that will display the truck's movement
        for (int j = 0; j < route.size(); j++) {
            truck.setCurrentLocation(route.get(j));
            map.revalidate();
            map.repaint();
            try {
                Thread.sleep(200);
            } catch (Exception ex) {

            }

            // update truck's current location
            start = truck.getCurrentLocation();
        }

        // mark an order as completed and make truck pause at house
        completedDeliveries.add(listOfDeliveries.get(i));
        neighborhood.setCompletedDeliveries(completedDeliveries);

        try {
            Thread.sleep(500);
        } catch (Exception ex) {
        }

        //clear list to determine route to next delivery
        truck.route.clearListOfLocations();
    }*/