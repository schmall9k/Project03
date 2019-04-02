/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

NeighborhoodGUI - general information for the JFrame

 */
package Simulation;

import java.util.ArrayList;
import javax.swing.JFrame;

public class NeighborhoodGUI extends JFrame {

    //  static variables for the size of the window
    public static final int FRAME_WIDTH  = 800;
    public static final int FRAME_HEIGHT = 800;

    private ArrayList<Address> deliveryLocations;
    NeighborhoodPanel          map;

    public NeighborhoodGUI(Truck truck, Neighborhood neighborhood) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Sandwich Truck Simulation");
        this.deliveryLocations = neighborhood.getSortedDeliveries();
        map = new NeighborhoodPanel(truck, neighborhood, deliveryLocations);
        add(map);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
