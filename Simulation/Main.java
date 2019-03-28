/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Main will run a simulation of the truck.

 */

package Simulation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Main {


    public static void main(String[] args) throws IOException {

        Neighborhood neighborhood = new Neighborhood(20);

        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.createQueue();

        Truck truck = new Truck();

        // calculates the truck's route distance in units
        int distance = truck.calculateRouteDistance();
        System.out.println("Truck's route distance: " + distance + " units");


        neighborhood.displayNeighborhood(truck);

        ArrayList<Address> listOfDeliveries = neighborhood.getSortedDeliveries();
        Address start = truck.getCurrentLocation();

        for (int i = 0; i < listOfDeliveries.size(); i++){
            ArrayList<Address> route = truck.calculateRoute(start, listOfDeliveries.get(i));
            for (int j = 0; j < route.size(); j++){
                truck.setCurrentLocation(route.get(j));
                neighborhood.displayNeighborhood(truck);
                start = truck.getCurrentLocation();
            }
            truck.clearListOfLocations();
        }


        /*ArrayList<Address> route = truck.calculateRoute(distCenter, address7);

        for (int i = 0; i < route.size(); i++) {

            truck.setCurrentLocation(route.get(i));
            neighborhood.displayNeighborhood(truck);
        }*/
    }
}