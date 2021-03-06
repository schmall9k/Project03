/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

NeighborhoodGUI - general information for the JFrame

 */
package Simulation;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;

// Kylie completed this class.

public class NeighborhoodGUI extends JFrame {

    //  static variables for the size of the window
    public static final int FRAME_WIDTH  = 800;
    public static final int FRAME_HEIGHT = 800;

    private ArrayList<Address> deliveryLocations;
    public NeighborhoodPanel   map;

    public NeighborhoodGUI(Truck truck, Neighborhood neighborhood) {

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Sandwich Truck Simulation");
        setBackground(Color.white);
        this.deliveryLocations = neighborhood.getSortedDeliveries();
        map = new NeighborhoodPanel(truck, neighborhood, deliveryLocations);
        add(map);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
