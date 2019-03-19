/*
 * CSCI234 - Software Engineering - Spring 2019
 * Description: Project03 - Sandwich Shop - Neighborhood JPanel Class
 * Authors: Kylie Norwood, Kiersten Schmall, and Elijah Ives
 */

package Simulation;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class NeighborhoodPanel extends JPanel {


    private NeighborhoodGUI gui;
    public static final int ROWS = 201;
    public static final int COLS = 201;
    public static final int CELL_WIDTH = NeighborhoodGUI.WIDTH / 201;
    public static final int CELL_HEIGHT = NeighborhoodGUI.HEIGHT / 201;

    NeighborhoodPanel(final NeighborhoodGUI gui) {
        this.gui = gui;
    }

    @Override
    public void paintComponent(Graphics g) {


        /*
        Tests that GUI draws the truck location.
        Address distCenter = new Address(910,"South",9,"","");
        drawTruckLocation(g, distCenter);*/

        drawHouse(g);
        drawDistributionCenter(g);
        try {
            drawHousesWithOrders(g);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void drawHouse(Graphics g) {
        g.setColor(Color.BLACK);
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (x % 10 == 0) {
                    if (y % 10 != 0)
                        g.drawRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                }
                if (x % 10 != 0) {
                    if (y % 10 == 0)
                        g.drawRect(x * CELL_WIDTH, y * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);

                }
            }
        }
    }

    // gui method that will display houses that have made an order (color = green)
    public void drawHousesWithOrders(Graphics g) throws IOException {
        g.setColor(Color.RED);


        int lineNum = 1;

        File file = new File("RandomAddresses.txt");
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);

            houseNumber = houseNumber / 10;
            streetNumber = streetNumber * 10;


            if (direction.equals("South"))
                g.fillRect(streetNumber * CELL_WIDTH, houseNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            else
                g.fillRect(houseNumber * CELL_WIDTH, streetNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);


            lineNum++;

        }
    }

    // gui method that will draw the distribution center onto the map (color = green)

    public void drawDistributionCenter(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(90 * CELL_WIDTH, 91 * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }

    // gui method that will draw the location of the truck (color = blue)
    public void drawTruckLocation(Graphics g, Address address) {
        g.setColor(Color.BLUE);
        int houseNumber = address.getHouseNumber() / 10;
        int streetNumber = address.getStreetNumber() * 10;

        if (address.getDirection().equals("South"))
            g.fillOval(streetNumber * CELL_WIDTH, houseNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
        else
            g.fillOval(houseNumber * CELL_WIDTH, streetNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
    }

}

