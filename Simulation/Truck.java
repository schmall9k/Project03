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

    // method that will calculate the route for the truck: will return an ArrayList of ALL locations the truck must hit during the route

    public ArrayList<Address> calculateRoute(Address truckLocation, Address houseLocation) {

        if (truckLocation.getDirection().equals(houseLocation.getDirection())) {
            if (truckLocation.getStreetNumber() == houseLocation.getStreetNumber()){
                int tempHouseNum = truckLocation.getHouseNumber();
                if (truckLocation.getHouseNumber() < houseLocation.getHouseNumber()) {
                    while(true){
                        tempHouseNum+=10;
                        Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                        listOfTruckLocations.add(nextLocation);
                        if (tempHouseNum == houseLocation.getHouseNumber())
                            break;
                    }
                }

                if (truckLocation.getHouseNumber() > houseLocation.getHouseNumber()) {
                    while (true){
                        tempHouseNum-=10;
                        Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                        listOfTruckLocations.add(nextLocation);
                        if (tempHouseNum == houseLocation.getHouseNumber())
                            break;
                    }
                }
            }

            /*else{
                while (truckLocation.getStreetNumber() != houseLocation.getStreetNumber()){

                    int tempStreetNum = truckLocation.getStreetNumber();
                    if (truckLocation.getStreetNumber() < houseLocation.getStreetNumber())
                    {
                        int tempHouseNum = truckLocation.getHouseNumber();
                        while (true){
                            Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                            listOfTruckLocations.add(nextLocation);
                            if (tempHouseNum > 1000){
                                if (tempHouseNum % 1000 == 0)
                                    break;
                            }
                            else {
                                if (tempHouseNum % 10 == 0)
                                    break;
                            }
                            tempHouseNum+=10;
                        }
                        tempStreetNum+=1;
                        Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum,"","");
                        listOfTruckLocations.add(nextLocation);
                    }

                    if (truckLocation.getStreetNumber() > houseLocation.getStreetNumber()){
                        int tempHouseNum = truckLocation.getHouseNumber();
                        while (true){
                            Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                            listOfTruckLocations.add(nextLocation);
                            if (tempHouseNum > 1000){
                                if (tempHouseNum % 1000 == 0)
                                    break;
                            }
                            else {
                                if (tempHouseNum % 10 == 0)
                                    break;
                            }
                            tempHouseNum-=10;
                        }
                        tempStreetNum-=1;
                        Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum,"","");
                        listOfTruckLocations.add(nextLocation);
                    }

                }
            }*/
        }
        return listOfTruckLocations;
    }

    public ArrayList<Address> getListOfTruckLocations() {
        return listOfTruckLocations;
    }
}
