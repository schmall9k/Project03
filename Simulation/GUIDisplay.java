/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

GUIDisplay: an observer in the observer pattern.

 */

package Simulation;

// Kylie wrote this class.

public class GUIDisplay implements Observer {

    private NeighborhoodGUI map;

    // constructor takes the map in which the display will be updated to
    public GUIDisplay(NeighborhoodGUI map){
        this.map = map;
    }

    // update method will update the gui based on where the truck is currently located
    @Override
    public void update(Address currentLocation) {

        map.revalidate();
        map.repaint();

        try {
            Thread.sleep(125);
        } catch (Exception ex) {}
    }
}
