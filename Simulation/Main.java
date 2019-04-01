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

        Address distCenter = new Address(510,"East",5,"","");

        Neighborhood neighborhood = new Neighborhood(10, distCenter);

        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.createQueue();

        // create Truck
        Truck truck = new Truck(distCenter);

        // Give the truck an established route
        truck.route = new OriginalRoute();


        //neighborhood.displayNeighborhood(truck);

        ArrayList<Address> listOfDeliveries = neighborhood.getSortedDeliveries();
        Address start = truck.getCurrentLocation();

        for (int i = 0; i < listOfDeliveries.size(); i++){
            truck.route.calculateRoute(start, listOfDeliveries.get(i));
            ArrayList<Address> route = truck.route.getListOfTruckLocations();
            for (int j = 0; j < route.size(); j++){
                truck.setCurrentLocation(route.get(j));
                neighborhood.displayNeighborhood(truck);
                start = truck.getCurrentLocation();
            }
            truck.route.clearListOfLocations();
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

    }
}