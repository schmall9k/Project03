/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Truck class that represents the delivery truck.

 */

package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Truck {

    //LocalTime deliveryTime;
    static int    DISTRIBUTION_HOUSE_NUM  = 910;
    static String DISTRIBUTION_DIRECTION  = "south";
    static int    DISTRIBUTION_STREET_NUM = 9;
    static String DISTRIBUTION_TIME1      = "";
    static String DISTRIBUTION_TIME2      = "";

    public ArrayList<Address> listOfTruckLocations; // list of all locations the truck will hit during its route
    public Address            currentLocation;      // truck's current location (starts at dist. center

    public Truck() {
        this.listOfTruckLocations = new ArrayList<>();
        this.currentLocation      = new Address(910,"South",9,"","");
    }

    public Truck(Address currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }


    // method that will calculate the distance of the route, in units
    public int calculateRouteDistance() throws IOException {
        int totalDistance = 0;
        Address truckLocation = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        Address dist = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        BufferedReader reader = new BufferedReader(new FileReader("AddressesByTime.txt"));
        String currentLine;
        String[] line;
        while((currentLine = reader.readLine()) != null) {
            line = currentLine.split(" ");
            Address houseLocation = new Address(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), line[4], line[5]);

            totalDistance += houseLocation.calculateDistanceFromLocation(truckLocation);
            //calculateRoute(truckLocation, houseLocation);

            truckLocation = houseLocation;
        }

        totalDistance += dist.calculateDistanceFromLocation(truckLocation);
        reader.close();

        return totalDistance;
    }

    public void clearListOfLocations(){
        listOfTruckLocations.clear();
    }

    // method that will calculate the route for the truck: will return an ArrayList of ALL locations the truck must hit during the route

    public ArrayList<Address> calculateRoute(Address truckLocation, Address houseLocation) {

        listOfTruckLocations.add(truckLocation);

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        // if truck and delivery location going in same direction and on the same street, just different house number
        if (sameDirection && sameStreetNumber) {
            int tempHouseNum = truckLocation.getHouseNumber();
            while (houseLocation.getHouseNumber() != tempHouseNum){
                if (truckLocation.getHouseNumber() < houseLocation.getHouseNumber()){
                    tempHouseNum+=10;
                }
                else
                    tempHouseNum-=10;
                Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                listOfTruckLocations.add(nextLocation);
            }
        }

        // if truck and house same direction, but different street number, house number doesn't matter
        if (sameDirection && !sameStreetNumber) {
            int tempStreetNum = truckLocation.getStreetNumber();
            int tempHouseNum  = truckLocation.getHouseNumber();
            int closestBlock  = truckLocation.getStreetNumber() * 100;
            int locationBlock = houseLocation.getStreetNumber() * 100;

            String switchDirection;
            if (houseLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";


            while (tempHouseNum != closestBlock) {
                if (tempHouseNum < closestBlock) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }
            }

            while (tempStreetNum != houseLocation.getStreetNumber()) {
                if (tempHouseNum % 100 == 0) {
                    while (tempHouseNum != locationBlock) {
                        if (tempHouseNum < locationBlock) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, "", ""));
                        } else {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, "", ""));
                        }
                    }
                    if (truckLocation.getStreetNumber() < houseLocation.getStreetNumber()) {
                        tempStreetNum += 1;
                    } else
                        tempStreetNum -= 1;
                }
            }

            tempHouseNum = closestBlock;
            while (tempHouseNum != houseLocation.getHouseNumber()) {
                if (tempHouseNum < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));
                }
            }
        }


        // directions are different, street number is the same
        if (!sameDirection && sameStreetNumber){
            int tempHouseNum = truckLocation.getHouseNumber();
            int closestBlock = truckLocation.getStreetNumber() * 100;
            while (closestBlock != tempHouseNum){
                if (tempHouseNum < closestBlock){
                    tempHouseNum+=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
                else{
                    tempHouseNum-=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
            }

            while (tempHouseNum != houseLocation.getHouseNumber()){
                if (tempHouseNum < houseLocation.getHouseNumber()){
                    tempHouseNum+=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
                else{
                    tempHouseNum-=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
            }
        }

        // different directions and different street numbers
        if (!sameDirection && !sameStreetNumber) {
            int locationBlock = houseLocation.getStreetNumber() * 100;
            //int thisBlock     = truckLocation.getStreetNumber() * 100;
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getStreetNumber();

            while (tempHouseNum != locationBlock) {
                if (tempHouseNum < locationBlock) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }
            }

            String switchDirection = houseLocation.getDirection();
            tempHouseNum = truckLocation.getStreetNumber() * 100;

            while (houseLocation.getHouseNumber() != tempHouseNum) {
                if (tempHouseNum < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;

                } else {
                    tempHouseNum -= 10;
                }
                Address nextLocation = new Address(tempHouseNum, switchDirection, houseLocation.getStreetNumber(), "", "");
                listOfTruckLocations.add(nextLocation);
            }
        }
        return listOfTruckLocations;
    }

    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<Address> getListOfTruckLocations() {
        return listOfTruckLocations;
    }
}
