package Simulation;


import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Neighborhood neighborhood = new Neighborhood();

        neighborhood.createRandomAddresses();
        neighborhood.createQueue();
        neighborhood.writeAddressesToFile();

        NeighborhoodGUI gui = new NeighborhoodGUI(neighborhood);
        

        System.out.println("PQ addresses are in order of delivery times: ");

        /*while (!map.queueOfAddresses.isEmpty()) {
            Address i = map.queueOfAddresses.poll();
            System.out.println(i);
        }*/
    }
}