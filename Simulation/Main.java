/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Main will run a simulation of the truck.

 */

package Simulation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main {


    public static void main(String[] args) throws IOException {

            Neighborhood neighborhood = new Neighborhood(20);

            neighborhood.createRandomAddresses();
            neighborhood.writeAddressesToFile();
            neighborhood.writeAddressesInOrderToFile();
            neighborhood.createQueue();

            Truck truck = new Truck();

            // calculates the truck's route distance in units
            int distance = truck.calculateRouteDistance();
            System.out.println("Truck's route distance: " + distance + " units");

            // truck starts at the distribution center
            Address distCenter = new Address(910, "South", 9, "", "");

            Address address7 = new Address(990, "South", 12, "", "");

            System.out.println(distCenter.calculateDistanceFromLocation(address7));

            ArrayList<Address> route = truck.calculateRoute(distCenter, address7);

            for (int i = 0; i < route.size(); i++) {

                truck.setCurrentLocation(route.get(i));
                neighborhood.displayNeighborhood(truck);
            }

        }
}

        /*
        print out the deliveries

        PriorityQueue<Address> listOfAddresses = neighborhood.getQueueOfAddresses();
        Iterator it = listOfAddresses.iterator();

        System.out.println("Priority queue addresses are: ");

        while (it.hasNext()) {
            System.out.println("Address: "+ it.next());
        }*/