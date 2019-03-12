package Simulation;

import java.io.*;
import java.util.PriorityQueue;

public class ReadFileToQueue {

    public static void main(String[] args) throws IOException {


        int lineNum = 1;

        File file = new File("Simulation/RandomAddresses.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));

        PriorityQueue<Address> pq = new PriorityQueue<>(100);

        String line;
        Address truckLocation = new Address(910, "South", 9);

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);

            Address address = new Address(houseNumber, direction, streetNumber);
            address.calculateDistanceFromTruck(truckLocation);
            System.out.println(lineNum + ": " + address.distanceFromTruck);

            pq.add(address);

            lineNum++;

        }


        System.out.println("PQ addresses are in order of distance from the truck (least to greatest): ");
        Object[] addresses = pq.toArray();
        for (int i = 0; i < addresses.length; i++)
            System.out.println(addresses[i]);

    }
}
