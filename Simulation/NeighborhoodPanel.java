package Simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class NeighborhoodPanel extends JPanel{
    private Truck truck;
    private Neighborhood neighborhood;
    private int width;

    public NeighborhoodPanel(Truck truck, Neighborhood neighborhood) {
        this.truck = truck;
        this.neighborhood = neighborhood;
        this.width = neighborhood.getNumberOfHousesOnStreet();
    }

    @Override
    public void setSize(int x, int y) {
        super.setSize(x, x);
    }


    @Override
    public void paint(Graphics g) {
        drawDistCenter(g);
        drawHouses(g);
        try {
            drawHousesWithOrders(g);
        } catch (IOException e) {
            e.printStackTrace();
        }

        drawTruckLocation(g);

    }

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

    public void drawDistCenter(Graphics g){
        g.setColor(Color.GREEN);
        drawALocation(g, neighborhood.getDistCenter());
    }

    public void drawTruckLocation(Graphics g){
        g.setColor(Color.BLUE);
        Address truckLocation = truck.getCurrentLocation();
        drawALocation(g,truckLocation);
    }


    public void drawHousesWithOrders(Graphics g) throws IOException {
        g.setColor(Color.RED);
        Iterator<Address> iterator = neighborhood.getSortedDeliveries().iterator();
        while (iterator.hasNext())
        {
            Address address = iterator.next();
            drawALocation(g,address);
        }
    }


    private void drawALocation(Graphics g, Address location){

        int houseNumber = location.getHouseNumber() / 10;
        int streetNumber = location.getStreetNumber() * 10;

        if (location.getDirection().equals("South"))
            g.fillOval(streetNumber * neighborhood.getCellWidth(), houseNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());
        else
            g.fillOval(houseNumber * neighborhood.getCellWidth(), streetNumber * neighborhood.getCellHeight(), neighborhood.getCellWidth(), neighborhood.getCellHeight());

    }

    /*public void drawRoute(Graphics g) throws IOException {
        ArrayList<Address> listOfDeliveries = neighborhood.getSortedDeliveries();
        Address start = truck.getCurrentLocation();

        for (int i = 0; i < listOfDeliveries.size(); i++){
            truck.getRoute().calculateRoute(start, listOfDeliveries.get(i));
            ArrayList<Address> route = truck.getRoute().getListOfTruckLocations();
            for (int j = 0; j < route.size(); j++){
                truck.setCurrentLocation(route.get(j));
                drawTruckLocation(g, route.get(j));
                update();
                start = truck.getCurrentLocation();
            }
            truck.getRoute().clearListOfLocations();
        }
    }

    public void update(){
        Timer repaintTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        repaintTimer.setDelay(1000);
        repaintTimer.start();
    }*/

    public void drawTruckLocation(Graphics g, Address truckLocation){
        g.setColor(Color.BLUE);
        drawALocation(g, truckLocation);
    }
}
