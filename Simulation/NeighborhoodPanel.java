/*
 * CSCI234 - Software Engineering - Spring 2019
 * Description: Project03 - Sandwich Shop - Neighborhood JPanel Class
 * Authors: Kylie Norwood, Kiersten Schmall, and Elijah Ives
 */

package Simulation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class NeighborhoodPanel extends JPanel {



    private NeighborhoodGUI gui;

    NeighborhoodPanel(final NeighborhoodGUI gui)
    {
        this.gui = gui;
    }

    @Override
    public void paintComponent(Graphics g){

        Neighborhood neighborhood = this.gui.getNeighborhood();



        neighborhood.drawHouse(g);
        neighborhood.drawDistributionCenter(g);
        try {
            neighborhood.drawHousesWithOrders(g);
        } catch (IOException e) {
            System.out.println("error");
        }

    }



}
