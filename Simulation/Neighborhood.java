/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Neighborhood class that represents the general structure of the neighborhood itself: showing houses, the truck, etc.

 */

package Simulation;

import java.io.*;
import java.util.*;
import java.util.List;

public class Neighborhood {

    public static final String FILENAME         = "RandomAddresses.txt";
    public static final String ORDERED_FILE     = "AddressesByTime.txt";
    public static final int    NUMBER_OF_ORDERS = 100;


    public ArrayList<Address>     addresses;         // random deliveries, before prioritized into queue
    public PriorityQueue<Address> queueOfAddresses;  // random deliveries
    public ArrayList<String>      deliveryTimes;     // random delivery times
    public ArrayList<Address>     sortedDeliveries;
    public Address                distCenter;

    public int numberOfHousesOnStreet;
    public int numberOfStreets;
    public int cellWidth;
    public int CellHeight;

    public Neighborhood(int numberOfStreets, Address distCenter) {

        this.addresses        = new ArrayList<>();
        this.queueOfAddresses = new PriorityQueue<>(NUMBER_OF_ORDERS);
        this.deliveryTimes    = new ArrayList<>();
        this.sortedDeliveries = new ArrayList<>();
        this.distCenter       = distCenter;


        this.numberOfStreets        = numberOfStreets;
        this.numberOfHousesOnStreet = numberOfStreets * 10 + 1;

        this.cellWidth     = NeighborhoodGUI.FRAME_WIDTH  / numberOfHousesOnStreet;
        this.CellHeight    = NeighborhoodGUI.FRAME_HEIGHT / numberOfHousesOnStreet;


    }

    // method that will generate the random addresses to be put in the file
    public ArrayList<Address> createRandomAddresses() {
        for (int i = 0; i < NUMBER_OF_ORDERS; i++) {

            // random house number
            String result = "";
            int range = (numberOfStreets * 100) - 10 + 1;
            int firstRand = new Random().nextInt(range) + 10;
            while (firstRand % 100 == 0){
                firstRand = new Random().nextInt(range) + 10;
            }
            firstRand = firstRand / 10;
            if (firstRand % 10 == 0)
                result = Integer.toString(firstRand);
            else
                result = Integer.toString(firstRand) + "0";
            firstRand = Integer.parseInt(result);

            // random direction
            String direction = "";
            int secRand = new Random().nextInt(2);
            if (secRand == 0)
                direction += "South";
            else
                direction += "East";

            // random street number
            int thirdRand = new Random().nextInt(numberOfStreets);


            // generate random delivery time
            List<Integer> givenListHours = Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 12);
            String time;
            String AMorPM; // true = AM, false = PM


            Random rand = new Random();
            int randHour = givenListHours.get(rand.nextInt(givenListHours.size()));
            int randMinute = rand.nextInt(60);


            if (randHour == 10 || randHour == 11) {
                AMorPM = "AM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            } else {
                AMorPM = "PM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            }

            while (deliveryTimes.contains(time)) {
                int randHourAgain = givenListHours.get(rand.nextInt(givenListHours.size()));
                int randMinuteAgain = rand.nextInt(60);

                if (randHour == 10 || randHour == 11) {
                    AMorPM = "AM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                } else {
                    AMorPM = "PM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                }
            }

            deliveryTimes.add(time);

            Address address = new Address(firstRand, direction, thirdRand, time, AMorPM);
            addresses.add(address);

        }

        return addresses;
    }

    // method that will generate the queue from reading the file of random addresses
    public void createQueue() throws IOException {

        File file = new File(FILENAME);
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);
            String streetLabel = addressArray[3];
            String deliveryTime = addressArray[4];
            String deliveryAMorPM = addressArray[5];

            Address address = new Address(houseNumber, direction, streetNumber, deliveryTime, deliveryAMorPM);

            queueOfAddresses.add(address);

        }

    }

    // method that will write above generated random addresses to the file
    public void writeAddressesToFile() throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME));
        for (int i = 0; i < addresses.size(); i++) {
            out.write(addresses.get(i).toString());
            out.write("\n");
        }

        out.close();
    }

    // method that will write the generated random addresses to a file in order of the order time
    public void writeAddressesInOrderToFile() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(ORDERED_FILE));
        while(!queueOfAddresses.isEmpty()) {
            sortedDeliveries.add(queueOfAddresses.poll());
            out.write(sortedDeliveries.toString());
            out.write("\n");
        }
        out.close();
    }

    // method that will place the delivery locations into a sorted array list (easier to work with than a queue)
    public ArrayList<Address> getSortedDeliveries() {
        while (!queueOfAddresses.isEmpty()){
            sortedDeliveries.add(queueOfAddresses.poll());
        }
        return sortedDeliveries;
    }

    // setter that will set the deliveries (used to remove deliveries that are completed)
    public void setSortedDeliveries(ArrayList<Address> sortedDeliveries){
        this.sortedDeliveries = sortedDeliveries;
    }


    // method that will calculate the distance of the route, in units
    public int calculateTrucksRouteDistance(Truck truck){
        int totalDistance = 0;

        Address truckLocation = truck.getCurrentLocation();

        for (int i = 0; i < sortedDeliveries.size(); i++) {
            Address houseLocation = sortedDeliveries.get(i);
            totalDistance += houseLocation.calculateDistanceFromLocation(truckLocation);
            truckLocation = houseLocation;

        }
        return totalDistance;
    }

    public int getNumberOfHousesOnStreet() {
        return numberOfHousesOnStreet;
    }

    public int getCellWidth() {
        return cellWidth;
    }

    public int getCellHeight() {
        return CellHeight;
    }

    public Address getDistCenter() {
        return distCenter;
    }
}