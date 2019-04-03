/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Route class that is created when Truck is created. Used to calculate possible routes that Truck may travel.

*/

package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface Route
{
    void calculateRoute(Address truckLocation, Address houseLocation);
    ArrayList<Address> getListOfTruckLocations();
    void clearListOfLocations();
    int costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses);
    int getRouteLength();
}
