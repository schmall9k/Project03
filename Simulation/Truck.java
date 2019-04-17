/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Truck class that represents the delivery truck.

 */

package Simulation;

import java.io.IOException;
import java.util.ArrayList;

// Kiersten modified to fit strategy pattern, Kylie modified after Route was changed to an interface.
// Kylie just changed methods so they would be compatible with the interface format.
public class Truck implements Vehicle{

    public Address currentLocation;      // truck's current location (starts at dist. center)
    public Route   route;                // truck's route that it will travel to make deliveries
    private ArrayList<Observer> observers;


    public Truck(Address startingLocation, Route route) {

        this.currentLocation = startingLocation;
        this.route           = route;
        this.observers       = new ArrayList();
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


    @Override
    public void registerObserver(Observer display) {
        observers.add(display);
    }

    @Override
    public void removeObserver(Observer display) {
        observers.remove(display);
    }


    @Override
    public void notifyObserver(Address currentLocation) throws IOException {
        for (Observer display : observers) {
            display.update(currentLocation);
        }
    }
}
