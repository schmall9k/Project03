package Simulation;


import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Simulation.Neighborhood map = new Simulation.Neighborhood();
        map.createMap();
        map.createRandomAddresses();
        map.writeAddressesToFile();
        map.createQueue();
        

        System.out.println("PQ addresses are in order of delivery times: ");

        /*while (!map.queueOfAddresses.isEmpty()) {
            Address i = map.queueOfAddresses.poll();
            System.out.println(i);
        }*/
    }
}