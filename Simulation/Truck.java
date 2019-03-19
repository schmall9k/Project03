package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Truck {

    //LocalTime deliveryTime;
    static int DISTRIBUTION_HOUSE_NUM = 910;
    static String DISTRIBUTION_DIRECTION = "south";
    static int DISTRIBUTION_STREET_NUM = 9;
    static String DISTRIBUTION_TIME1 = " ";
    static String DISTRIBUTION_TIME2= " ";

    public Truck() {
    }

    public int calculateRouteDistance() throws IOException {
        int totalDistance = 0;
        Address truckLocation = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        Address dist = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        BufferedReader reader = new BufferedReader(new FileReader("RandomAddresses.txt"));
        String currentLine;
        String[] line;
        while((currentLine = reader.readLine()) != null) {
            line = currentLine.split(" ");
            Address houseLocation = new Address(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), line[4], line[5]);
            totalDistance += houseLocation.calculateDistanceFromTruck(truckLocation);
            truckLocation = houseLocation;

        }

        totalDistance += dist.calculateDistanceFromTruck(truckLocation);
        reader.close();

        return totalDistance;
    }
}
