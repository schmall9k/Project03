/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Main will run a simulation of the truck.

 */

package Simulation;

import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {

        // declare what address will be the distribution center
        Address distCenter = new Address(510,"East",5,"","");

        Neighborhood neighborhood = new Neighborhood(10, distCenter);

        // create random addresses, write them to files, and create the queue
        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.writeAddressesInOrderToFile();
        neighborhood.createQueue();

        // determine what route the truck will use (declare its type)
        Route trucksRoute = new OriginalRoute();

        // create Truck
        Truck truck = new Truck(distCenter, trucksRoute);

        // Give the truck an established route
        truck.setRoute(new OriginalRoute());

        // create GUI display
        NeighborhoodGUI map = new NeighborhoodGUI(truck, neighborhood);

        // access the deliveries and determine truck's starting point
        ArrayList<Address> listOfDeliveries = neighborhood.getSortedDeliveries();
        Address start = truck.getCurrentLocation();

        // runs through list of deliveries, calculates each route, and displays the simulation
        for (int i = 0; i < listOfDeliveries.size(); i++){
            truck.route.calculateRoute(start, listOfDeliveries.get(i));
            System.out.println(listOfDeliveries.get(i));
            truck.setCurrentOrder(listOfDeliveries.get(i));
            ArrayList<Address> route = truck.route.getListOfTruckLocations();

            // loop that will display the truck's movement
            for (int j = 0; j < route.size(); j++){
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

            // remove order after completed and make truck pause
            listOfDeliveries.remove(i);
            neighborhood.setSortedDeliveries(listOfDeliveries);

            try {
                Thread.sleep(500);
            } catch (Exception ex) {
            }

            //clear list to determine route to next delivery
            truck.route.clearListOfLocations();
        }

        System.out.println("The route's distance was: " + truck.getRoute().getRouteLength());
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