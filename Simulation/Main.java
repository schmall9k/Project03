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

        Neighborhood neighborhood = new Neighborhood();

        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.writeAddressesInOrderToFile();
        neighborhood.createQueue();


        Truck truck = new Truck();

        // calculates the truck's route distance in units
        int distance = truck.calculateRouteDistance();
        System.out.println("Truck's route distance: " + distance + " units");


        /* This simple simulation displays that our route method correctly generates route when truck location and
        * delivery location are in the same direction and on the same street.
        */

        // truck starts at the distribution center
        Address distCenter = new Address(910, "South", 9, "", "");

        // truck going to 1210 South 9th St.
        Address address7 = new Address(1210, "South", 9, "", "");

        ArrayList<Address> route = truck.calculateRoute(distCenter, address7);

        for (int i = 0; i < route.size(); i++) {

            neighborhood.drawNeighborhood(neighborhood.getQueueOfAddresses(), route.get(i));

            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {

            }
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