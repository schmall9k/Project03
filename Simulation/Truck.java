/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Truck class that represents the delivery truck.

 */

package Simulation;

public class Truck {

    public Address currentLocation;      // truck's current location (starts at dist. center)
    public Route   route;                // truck's route that it will travel to make deliveries

    public Truck(Address startingLocation, Route route) {

        this.currentLocation = startingLocation;
        this.route           = route;
    }

    public Address getCurrentLocation() {
        return currentLocation;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }

    public void setCurrentLocation(Address currentLocation) {
        this.currentLocation = currentLocation;
    }


}
