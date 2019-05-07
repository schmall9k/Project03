/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

A concrete observer in the observer pattern: a monitor display.
 */

package Simulation;

// Kylie wrote this class.

public class MonitorDisplay implements Observer {
    @Override
    public void update(Address currentLocation) {
        System.out.println(currentLocation);
        try {
            Thread.sleep(300);
        } catch (Exception ex) {
        }
    }
}
