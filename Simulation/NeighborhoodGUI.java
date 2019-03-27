package Simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import Simulation.Truck;

public class NeighborhoodGUI extends JFrame {

    public static final int FRAME_WIDTH  = 1000;
    public static final int FRAME_HEIGHT = 1000;

    public NeighborhoodGUI(Truck truck, Neighborhood neighborhood) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle("Sandwich Truck Simulation");
        NeighborhoodPanel map = new NeighborhoodPanel(truck, neighborhood);
        //map.setSize(400,400);
        add(map);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {

        }
    }
}
