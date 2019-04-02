/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Truck class that represents the delivery truck.

 */

package Simulation;

public class Truck {

    public Address currentLocation;      // truck's current location (starts at dist. center)
    public Route   route;                // truck's route that it will travel to make deliveries
    public Address currentOrder;

    public Truck(Address startingLocation, Route route) {

        this.currentLocation = startingLocation;
        this.route           = route;
    }

    public void setCurrentOrder(Address currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Address getCurrentOrder() {
        return currentOrder;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }

    public Route getRoute() {
        return route;
    }

    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }


}
