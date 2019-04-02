/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Route class that is created when Truck is created. Used to calculate possible routes that Truck may travel.

*/

package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Route
{

    public ArrayList<Address> listOfTruckLocations;
    public int                routeLength;

    public Route()
    {
        this.listOfTruckLocations = new ArrayList<>();
        this.routeLength          = 0;
    }

    public ArrayList<Address> getListOfTruckLocations() {
        return listOfTruckLocations;
    }

    public void clearListOfLocations(){
        listOfTruckLocations.clear();
    }

    public void calculateRoute(Address truckLocation, Address houseLocation)
    {
    }

    // The following method is for calculating the cost effectiveness of the route.
    //  - move from one address to another in 1 unit of time
    //  - a stop at a delivery address takes 5 units of time
    //  - a right hand turn takes 2 units of time
    //  - a left hand turn takes 4 units of time
    //  - time to prepare a food order is 5 units of time
    //  - compute the total length of each route in distance and time

    public int costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses)
    {
        int cost = 0;

        // get initial info of truck
        String direction = route.get(0).direction;
        int streetNumber = route.get(0).streetNumber;
        int houseNumber = route.get(0).houseNumber;

        String directionTraveling = "";
        // get initial direction of truck.. not South/East, but traveling up, down, left, right.
        if (direction.equals("South") && route.get(1).direction.equals("South"))
        {
            if (houseNumber > route.get(1).houseNumber)
            {
                directionTraveling = "up";
            }
            else {
                directionTraveling = "down";
            }
        }
        else if (direction.equals("East") && route.get(1).direction.equals("East"))
        {
            if (houseNumber > route.get(1).houseNumber)
            {
                directionTraveling = "left";
            }
            else {
                directionTraveling = "right";
            }
        }
        else {
            if (direction.equals("South"))
            {
                directionTraveling = "up";
            }
            else {

                directionTraveling = "left";
            }
        }

        // cycle through list of addresses that truck is traveling
        for (int i = 1; i < route.size(); i++) {

            // adding 1 for moving from one address to another, right now includes intersections.
            cost += 1;

            /* adding 5 for each stop at a delivery location
            if (queueOfAddresses.contains(route.get(i))) {
                cost = cost + 5;
            }*/

            // checking to see if direction of address is different from previous direction.
            // need to know which way truck is turning
            if (!direction.equals(route.get(i).direction)) {
                streetNumber = streetNumber * 100; // will turn 9th into 900 or 5th into 500

                if (route.get(i).houseNumber > streetNumber) {
                    if (directionTraveling.equals("up")) {
                        // traveling up, turn right
                        cost += 2;
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += 4;
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += 4;
                        directionTraveling = "down";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += 2;
                        directionTraveling = "down";
                    }
                }
                // else if route.get(i).houseNumber < streetNumber
                else {
                    if (directionTraveling.equals("up")) {
                        // traveling up, turn right
                        cost += 4;
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += 2;
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += 2;
                        directionTraveling = "up";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += 4;
                        directionTraveling = "up";
                    }
                }
            }
            direction = route.get(i).direction;
            streetNumber = route.get(i).streetNumber;
            houseNumber = route.get(i).houseNumber; // Not currently being used but updated anyway
        }

        // when you reach the last location for delivery on the route.
        // will NOT need if using priority queue
        cost = cost + 5;

        // if time < 5, need enough time to make order. Order takes 5 units of time to make.
        // Can make food while truck is moving. Must not include the 5 units of time it takes to stop at each
        // delivery
        if ((cost-5) < 5)
        {
            while ((cost-5) < 5)
            {
                cost++;
            }
        }
        return cost;
    }

    public int getRouteLength() {
        return routeLength;
    }
}
