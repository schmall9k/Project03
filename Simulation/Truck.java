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
import java.util.PriorityQueue;

public class Truck {

    static int          DISTRIBUTION_HOUSE_NUM  = 910;
    static String       DISTRIBUTION_DIRECTION  = "south";
    static int          DISTRIBUTION_STREET_NUM = 9;
    static String       DISTRIBUTION_TIME1      = "";
    static String       DISTRIBUTION_TIME2      = "";

    public Address      currentLocation;      // truck's current location (starts at dist. center)
    public Route        route;                // truck's route that it will travel to make deliveries

    public Truck(Address startingLocation) {

        this.currentLocation      = startingLocation;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }


    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }


}
