package Simulation;

// Kylie wrote this class.

import java.io.IOException;

public interface Vehicle {

    // have an array list of observers
    void registerObserver(Observer display);
    void removeObserver(Observer display);

    // send the truck's current location and route
    // loop through the array list and call update on each observer
    void notifyObserver(Address truckCurLocation) throws IOException;

}
