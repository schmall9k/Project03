package Simulation;


import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Neighborhood neighborhood = new Neighborhood();

        neighborhood.createRandomAddresses();
        neighborhood.writeAddressesToFile();
        neighborhood.createQueue();


        NeighborhoodGUI gui = new NeighborhoodGUI(neighborhood);


        Truck truck = new Truck();
        int distance = truck.calculateRouteDistance();
        System.out.println(distance);

        //truck.calculateRoute(neighborhood.queueOfAddresses);
        

        System.out.println("PQ addresses are in order of delivery times: ");

        /*while (!map.queueOfAddresses.isEmpty()) {
            Address i = map.queueOfAddresses.poll();
            System.out.println(i);
        }*/
    }
}