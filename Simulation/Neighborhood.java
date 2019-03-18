package Simulation;


import javax.swing.text.DateFormatter;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.LocalTime.of;
import static java.time.LocalTime.parse;

public class Neighborhood {

    public static final int ROWS = 201;
    public static final int COLS = 201;
    public static final String FILENAME = "RandomAddresses.txt";

    private String[][] neighborhood;
    public ArrayList<Address> addresses;
    public PriorityQueue<Address> queueOfAddresses;
    ArrayList<String> deliveryTimes = new ArrayList<>();

    public Neighborhood() {

        this.neighborhood     = new String[ROWS][COLS];
        this.addresses        = new ArrayList<>();
        this.queueOfAddresses = new PriorityQueue<>(100);

    }

    //method that will generate the random addresses to be put in the file
    public ArrayList<Address> createRandomAddresses(){
        for (int i = 0; i < 100; i++)
        {

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


            if (randHour == 10 || randHour == 11)
            {
                AMorPM = "AM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            }
            else {
                AMorPM = "PM";
                time = "" + randHour + ":" + String.format("%02d", randMinute);
            }

            while (deliveryTimes.contains(time))
            {
                int randHourAgain = givenListHours.get(rand.nextInt(givenListHours.size()));
                int randMinuteAgain = rand.nextInt(60);

                if (randHour == 10 || randHour == 11)
                {
                    AMorPM = "AM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                }
                else {
                    AMorPM = "PM";
                    time = "" + randHourAgain + ":" + String.format("%02d", randMinuteAgain);
                }
            }

            deliveryTimes.add(time);

            Address address = new Address(firstRand,direction,thirdRand,time,AMorPM);
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
        Address truckLocation = new Address(910, "South", 9, "10:00", " am");


        while ((line = in.readLine()) != null) {
            String[] addressArray = line.split(" ");
            int houseNumber = Integer.parseInt(addressArray[0]);
            String direction = addressArray[1];
            int streetNumber = Integer.parseInt(addressArray[2]);
            String streetLabel = addressArray[3];
            String deliveryTime = addressArray[4];
            String deliveryAMorPM = addressArray[5];

            Address address = new Address(houseNumber, direction, streetNumber, deliveryTime, deliveryAMorPM);

            //address.calculateDistanceFromTruck(truckLocation);

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
