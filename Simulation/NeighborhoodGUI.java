/*
 * CSCI234 - Software Engineering - Spring 2019
 * Description: Project03 - Sandwich Shop - Neighborhood GUI Class
 * Authors: Kylie Norwood, Kiersten Schmall, and Elijah Ives
 */

package Simulation;

import javax.swing.*;
import java.awt.*;

public class NeighborhoodGUI extends JFrame {
    private Neighborhood neighborhood;


    public static final int    WIDTH        = 800;
    public static final int    HEIGHT       = 800;
    public static final String TITLE        = "Neighborhood Display";

    NeighborhoodGUI(final Neighborhood neighborhood){
        setSize(WIDTH,HEIGHT);
        setTitle(TITLE);

        this.neighborhood = neighborhood;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        NeighborhoodPanel neighborhoodPanel = new NeighborhoodPanel(this);
        neighborhoodPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(neighborhoodPanel);

        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    Neighborhood getNeighborhood(){
        return neighborhood;
    }
}
