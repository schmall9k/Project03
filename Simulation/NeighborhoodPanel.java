package Simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JPanel;

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
                        g.drawRect(x * Neighborhood.CELL_WIDTH, y * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
                }
                if (x % 10 != 0) {
                    if (y % 10 == 0)
                        g.drawRect(x * Neighborhood.CELL_WIDTH, y * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);

                }
            }
        }
    }

    public void drawDistCenter(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(90 * Neighborhood.CELL_WIDTH, 91 * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
    }

    public void drawTruckLocation(Graphics g){
        g.setColor(Color.BLUE);
        Address truckLocation = truck.getCurrentLocation();
        int houseNumber = truckLocation.getHouseNumber() / 10;
        int streetNumber = truckLocation.getStreetNumber() * 10;

        if (truckLocation.getDirection().equals("South"))
            g.fillOval(streetNumber * Neighborhood.CELL_WIDTH, houseNumber * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
        else
            g.fillOval(houseNumber * Neighborhood.CELL_WIDTH, streetNumber * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
    }

    public void drawHousesWithOrders(Graphics g) throws IOException {
        g.setColor(Color.RED);
        Iterator<Address> iterator = neighborhood.getSortedDeliveries().iterator();
        while (iterator.hasNext())
        {
            Address address = iterator.next();
            int houseNumber = address.getHouseNumber() / 10;
            int streetNumber = address.getStreetNumber() * 10;


            if (address.getDirection().equals("South"))
                g.fillRect(streetNumber * Neighborhood.CELL_WIDTH, houseNumber * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
            else
                g.fillRect(houseNumber * Neighborhood.CELL_WIDTH, streetNumber * Neighborhood.CELL_HEIGHT, Neighborhood.CELL_WIDTH, Neighborhood.CELL_HEIGHT);
        }

    }
}
