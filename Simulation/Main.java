package Simulation;


import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        Neighborhood map = new Neighborhood();
        map.createMap();
        map.createRandomAddresses();
        map.writeAddressesToFile();
        map.createQueue();

        // will give us the addresses in order of distance from the truck (least to greatest)
        /*System.out.println("PQ addresses are in order of distance from the truck (least to greatest): ");
        Object[] addresses = map.queueOfAddresses.toArray();
        for (int i = 0; i < addresses.length; i++)
            System.out.println(addresses[i]);*/
    }
}