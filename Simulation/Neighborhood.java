package Simulation;


import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Neighborhood {

    public static final int ROWS = 201;
    public static final int COLS = 201;
    public static final String FILENAME = "RandomAddresses.txt";

    private String[][] neighborhood;
    public ArrayList<Address> addresses;
    public PriorityQueue<Address> queueOfAddresses;

    public Neighborhood() {

        this.neighborhood     = new String[ROWS][COLS];
        this.addresses        = new ArrayList<>();
        this.queueOfAddresses = new PriorityQueue<>(100);

    }

    //method that will generate the random addresses to be put in the file
    public ArrayList<Address> createRandomAddresses(){
        for (int i = 0; i < 100; i++)
        {
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
            Address address = new Address(firstRand,direction,thirdRand);
            //System.out.println(address);
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

    //method that will generate the queue from reading the file
    public void createQueue() throws IOException {
        int lineNum = 1;

        File file = new File(FILENAME);
        BufferedReader in = new BufferedReader(new FileReader(file));

        String line;
        Address truckLocation = new Address(910, "South", 9);

        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);

            Address address = new Address(houseNumber, direction, streetNumber);
            address.calculateDistanceFromTruck(truckLocation);

            //will print out the line number and the distance from the truck, just here to check answers
            //System.out.println(lineNum + ": " + address.distanceFromTruck);

            queueOfAddresses.add(address);

            lineNum++;

        }
    }


    //method that will create a 2D array to display the map
    public void createMap() {

        //Denote where houses exist: "*" = houses
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLS; y++) {
                if (x % 10 == 0) {
                    if (y % 10 == 0)
                        neighborhood[x][y] = "- ";
                    else
                        neighborhood[x][y] = "* ";
                }
                if (x % 10 != 0) {
                    if (y % 10 == 0)
                        neighborhood[x][y] = "* ";
                    else
                        neighborhood[x][y] = "  ";
                }
            }
        }

        //Denote distribution center
        neighborhood[91][90] = "@";
    }

    public String[][] getNeighborhood() {
        return neighborhood;
    }
}
