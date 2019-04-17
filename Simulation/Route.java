/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Route class that is created when Truck is created. Used to calculate possible routes that Truck may travel.

*/

package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

// Kiersten wrote this class, Kylie later changed to an interface.

public interface Route
{
    // determines the route of the truck
    void calculateRoute(Address truckLocation, Address houseLocation);

    // gets the list of truck locations the truck will "hit" during the route
    ArrayList<Address> getListOfTruckLocations();

    // clears the trucks locations so we can compute the next route the truck will take
    void clearListOfLocations();

    // computes the cost of the truck's route
    int costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses);

    // determines the length of the truck's route in units
    int getRouteLength();
}
