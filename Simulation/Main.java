/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Main will run a simulation of the truck.

 */

package Simulation;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) throws IOException {

        Neighborhood neighborhood = new Neighborhood();

        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.writeAddressesInOrderToFile();
        neighborhood.createQueue();



        //Simple simulation to show the truck's movement
        ArrayList<Address> randomAddresses = new ArrayList<>();

        Address distCenter = new Address(910,"South",9,"","");
        randomAddresses.add(distCenter);
        Address address1 = new Address(920, "South", 9, "", "");
        randomAddresses.add(address1);
        Address address2 = new Address(930, "South", 9, "", "");
        randomAddresses.add(address2);
        Address address3 = new Address(940, "South", 9, "", "");
        randomAddresses.add(address3);
        Address address4 = new Address(950, "South", 9, "", "");
        randomAddresses.add(address4);
        Address address5 = new Address(960, "South", 9, "", "");
        randomAddresses.add(address5);
        Address address6 = new Address(970, "South", 9, "", "");
        randomAddresses.add(address6);
        Address address7 = new Address(980, "South", 9, "", "");
        randomAddresses.add(address7);
        Address address8 = new Address(990, "South", 9, "", "");
        randomAddresses.add(address8);
        Address address9 = new Address(1000, "South", 9, "", "");
        randomAddresses.add(address9);
        Address address10 = new Address(910, "East", 10, "", "");
        randomAddresses.add(address10);
        Address address11 = new Address(920, "East", 10, "", "");
        randomAddresses.add(address11);
        Address address12 = new Address(930, "East", 10, "", "");
        randomAddresses.add(address12);
        Address address13 = new Address(940, "East", 10, "", "");
        randomAddresses.add(address13);
        Address address14 = new Address(950, "East", 10, "", "");
        randomAddresses.add(address14);
        Address address15 = new Address(960, "East", 10, "", "");
        randomAddresses.add(address15);



        for (int i = 0; i < randomAddresses.size(); i++){

            neighborhood.drawNeighborhood(neighborhood.getQueueOfAddresses(), randomAddresses.get(i));


            try {
                Thread.sleep(250);
            }
            catch (InterruptedException ex) {

            }
        }


        Truck truck = new Truck();
        int distance = truck.calculateRouteDistance();
        System.out.println("Truck's route distance: " + distance + "units");


        /*Address distCenter = new Address(910,"South",9,"","");
        Address address7 = new Address(980, "South", 9, "", "");

        ArrayList<Address> route = truck.calculateRoute(distCenter,address7);

        for (int i = 0; i < route.size(); i++){

            neighborhood.drawNeighborhood(neighborhood.getQueueOfAddresses(), route.get(i));
        }*/

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