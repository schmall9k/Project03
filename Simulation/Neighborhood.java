/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Neighborhood class that represents the general structure of the neighborhood itself: showing houses, the truck, etc.

 */

package Simulation;


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Neighborhood {

    public static final int ROWS             = 201;
    public static final int COLS             = 201;
    public static final String FILENAME      = "RandomAddresses.txt";
    public static final String ORDERED_FILE = "AddressesByTime.txt";

    static final int HEIGHT = 782, WIDTH = 761;
    public static final int CELL_WIDTH   = 800 / 201;
    public static final int CELL_HEIGHT  = 800 / 201;

    public ArrayList<Address>     addresses;
    public PriorityQueue<Address> queueOfAddresses;
    ArrayList<String>             deliveryTimes;

    public Neighborhood() {

        this.addresses        = new ArrayList<>();
        this.queueOfAddresses = new PriorityQueue<>(100);
        this.deliveryTimes    = new ArrayList<>();


    }

    //method that will generate the random addresses to be put in the file
    public ArrayList<Address> createRandomAddresses() {
        for (int i = 0; i < 100; i++) {

            // solving for the address
            String result = "";
            int range = 2000 - 10 + 1;
            int firstRand = new Random().nextInt(range) + 10;
            firstRand = firstRand / 10;
            result = Integer.toString(firstRand) + "0";
            firstRand = Integer.parseInt(result);

            String direction = "";
            int secRand = new Random().nextInt(2);
            if (secRand == 0)
                direction += "South";
            else
                direction += "East";

            int thirdRand = new Random().nextInt(20);


            // solving for random delivery time
            List<Integer> givenListHours = Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 12);
            String time;
            String AMorPM; // true = AM, false = PM


            Random rand = new Random();
            int randHour = givenListHours.get(rand.nextInt(givenListHours.size()));
            int randMinute = rand.nextInt(60);


            if (randHour == 10 || randHour == 11) {
                AMorPM = "AM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            } else {
                AMorPM = "PM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            }

            while (deliveryTimes.contains(time)) {
                int randHourAgain = givenListHours.get(rand.nextInt(givenListHours.size()));
                int randMinuteAgain = rand.nextInt(60);

                if (randHour == 10 || randHour == 11) {
                    AMorPM = "AM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                } else {
                    AMorPM = "PM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                }
            }

            deliveryTimes.add(time);

            Address address = new Address(firstRand, direction, thirdRand, time, AMorPM);
            addresses.add(address);

        }

        return addresses;
    }

    //method that will write above generated random addresses to the file
    public void writeAddressesToFile() throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter(FILENAME));
        for (int i = 0; i < addresses.size(); i++) {
            out.write(addresses.get(i).toString());
            out.write("\n");
        }

        out.close();
    }

    //method that will write addresses of orders to the file IN ORDER of order time
    public void writeAddressesInOrderToFile() throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(ORDERED_FILE));
        for (int i = 0; i < addresses.size(); i++) {
            out.write(addresses.get(i).toString());
            out.write("\n");
        }

        out.close();
    }

    //method that will generate the queue from reading the file
    public void createQueue() throws IOException {

        File file = new File(FILENAME);
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);
            String streetLabel = addressArray[3];
            String deliveryTime = addressArray[4];
            String deliveryAMorPM = addressArray[5];

            Address address = new Address(houseNumber, direction, streetNumber, deliveryTime, deliveryAMorPM);

            queueOfAddresses.add(address);

        }

    }

    public PriorityQueue<Address> getQueueOfAddresses() {
        return queueOfAddresses;
    }

    //method that will display the simulation of the neighborhood: draws neighborhood, houses with current orders, and movement of the truck.
    public static void drawNeighborhood(PriorityQueue<Address> addresses, Address truckLocation)
    {
        JFrame neighborhood = new JFrame();
        JPanel canvas = new JPanel() {
            public void paintComponent(Graphics g) {
                //draw houses
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

                //draw dist center
                g.setColor(Color.GREEN);
                g.fillRect(90 * CELL_WIDTH, 91 * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);

                //draw houses with orders
                g.setColor(Color.RED);
                Iterator<Address> iterator = addresses.iterator();
                while (iterator.hasNext())
                {
                    Address address = iterator.next();
                    int houseNumber = address.getHouseNumber() / 10;
                    int streetNumber = address.getStreetNumber() * 10;


                    if (address.getDirection().equals("South"))
                        g.fillRect(streetNumber * CELL_WIDTH, houseNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                    else
                        g.fillRect(houseNumber * CELL_WIDTH, streetNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                }

                //draw truck location
                g.setColor(Color.BLUE);
                int houseNumber = truckLocation.getHouseNumber() / 10;
                int streetNumber = truckLocation.getStreetNumber() * 10;

                if (truckLocation.getDirection().equals("South"))
                    g.fillOval(streetNumber * CELL_WIDTH, houseNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
                else
                    g.fillOval(houseNumber * CELL_WIDTH, streetNumber * CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT);
            }

        };
        neighborhood.getContentPane().add(canvas);
        //neighborhood.revalidate();
        //neighborhood.repaint();
        neighborhood.setTitle("Neighborhood");
        neighborhood.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        neighborhood.setSize(WIDTH, HEIGHT);
        neighborhood.setLocationRelativeTo(null); // center on screen
        neighborhood.setVisible(true);
    }
}
