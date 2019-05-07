/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

NeighborhoodPanel (JPanel) class that will handle all drawing of shapes and components of the graphical user interface.

 */

package Simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

// Kylie wrote this class.

public class NeighborhoodPanel extends JPanel{
    private Truck              truck;
    private Neighborhood       neighborhood;
    private int                width;
    private ArrayList<Address> deliveryLocations;

    public NeighborhoodPanel(Truck truck, Neighborhood neighborhood, ArrayList<Address> deliveryLocations){
        this.truck             = truck;
        this.neighborhood      = neighborhood;
        this.deliveryLocations = deliveryLocations;
        this.width             = neighborhood.getNumberOfHousesOnStreet();
    }

    @Override
    public void setSize(int x, int y) {
        super.setSize(x, x);
    }


    // displays all graphics for the map
    @Override
    public void paint(Graphics g) {

        drawHouses(g);
        drawHousesWithOrders(g, deliveryLocations);
        drawCompletedOrders(g);
        drawTruckLocation(g);
        drawDistCenter(g);

    }

    // displays the houses/blocks/streets in the neighborhood
    public void drawHouses(Graphics g){
        g.setColor(Color.BLACK);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < width; y++) {
                if (x % 10 == 0) {
                    if (y % 10 != 0)
                        g.drawRect(x * neighborhood.getCellWidth(), y * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
                }
                if (x % 10 != 0) {
                    if (y % 10 == 0)
                        g.drawRect(x * neighborhood.getCellWidth(), y * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());

                }
            }
        }
    }

    // displays the distribution center
    public void drawDistCenter(Graphics g){
        g.setColor(Color.GREEN);
        drawALocation(g, neighborhood.getDistCenter(), false);
    }

    // draws the truck's location
    public void drawTruckLocation(Graphics g){
        g.setColor(Color.BLUE);
        Address truckLocation = truck.getCurrentLocation();
        drawALocation(g,truckLocation, true);
    }

    // draws the houses that have current orders. method takes an arrayList of the REMAINING deliveries (handled in main)
    public void drawHousesWithOrders(Graphics g, ArrayList<Address> deliveryLocations) {
        g.setColor(Color.RED);
        Iterator<Address> iterator = deliveryLocations.iterator();
        while (iterator.hasNext())
        {
            Address address = iterator.next();
            drawALocation(g,address, true);
        }
    }

    // method that will mark a house as completed when a delivery is finished
    private void drawCompletedOrders(Graphics g){
        g.setColor(Color.white);
        Iterator<Address> iterator = neighborhood.getCompletedDeliveries().iterator();
        while (iterator.hasNext()){
            Address address = iterator.next();
            drawALocation(g, address, true);
            drawOriginalHouse(g, address);
            g.setColor(Color.white);
        }

    }

    // helper method that will put the house back to its original color and format
    private void drawOriginalHouse(Graphics g, Address address)
    {
        int houseNumber = address.getHouseNumber() / 10;
        int streetNumber = address.getStreetNumber() * 10;

        g.setColor(Color.black);

        if (address.getDirection().equals("South"))
            g.drawRect(streetNumber * neighborhood.getCellWidth(), houseNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
        else
            g.drawRect(houseNumber * neighborhood.getCellWidth(), streetNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());

    }


    // helper method that will take an address and draw that location onto the display
    private void drawALocation(Graphics g, Address location, Boolean isCircle) {

        int houseNumber = location.getHouseNumber() / 10;
        int streetNumber = location.getStreetNumber() * 10;

        if (isCircle){
            if (location.getDirection().equals("South"))
                g.fillOval(streetNumber * neighborhood.getCellWidth(), houseNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
            else
                g.fillOval(houseNumber * neighborhood.getCellWidth(), streetNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());

        }

        else{
            if (location.getDirection().equals("South")) {
                g.fillRect(streetNumber * neighborhood.getCellWidth(), houseNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
            }
            else {
                g.fillRect(houseNumber * neighborhood.getCellWidth(), streetNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
            }
        }

    }
}
