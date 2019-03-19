package Simulation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

public class Address implements Comparable<Address> {

    public int houseNumber;
    public String direction;
    public int streetNumber;
    public int distanceFromTruck;
    public String deliveryTime;
    public String deliveryAMorPM;
    //LocalTime deliveryTime;
    static int DISTRIBUTION_HOUSE_NUM = 910;
    static String DISTRIBUTION_DIRECTION = "south";
    static int DISTRIBUTION_STREET_NUM = 9;
    static String DISTRIBUTION_TIME1 = " ";
    static String DISTRIBUTION_TIME2= " ";


    public Address(int houseNumber, String direction, int streetNumber, String deliveryTime, String deliveryAMorPM) {
        this.houseNumber = houseNumber;
        this.direction = direction;
        this.streetNumber = streetNumber;
        this.deliveryTime = deliveryTime;
        this.deliveryAMorPM = deliveryAMorPM;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getDirection() {
        return direction;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public int getDistanceFromTruck() {
        return distanceFromTruck;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getDeliveryAMorPM() {
        return deliveryAMorPM;
    }


    public int calculateDistanceFromTruck(Address truckLocation)
    {
        if (this.direction.equals(truckLocation.direction))
        {
            distanceFromTruck = truckLocation.houseNumber - this.houseNumber;
            if (distanceFromTruck < 0)
                distanceFromTruck = distanceFromTruck * -1;
        }

        else {
            int convertedStreetNumAdd = 0;
            int convertedStreetNumTruck = 0;
            int distance1 = 0;
            int distance2 = 0;

            convertedStreetNumAdd = this.streetNumber * 100;
            convertedStreetNumTruck = truckLocation.streetNumber * 100;

            distance1 = truckLocation.houseNumber - convertedStreetNumAdd;
            distance2 = convertedStreetNumTruck - this.houseNumber;

            if (distance1 < 0)
                distance1 *= -1;
            if (distance2 < 0)
                distance2 *= -1;

            distanceFromTruck = distance1 + distance2;

        }
        return distanceFromTruck;
    }

    public int calculateRouteDistance() throws IOException {
        int totalDistance = 0;
        Address truckLocation = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        Address dist = new Address(DISTRIBUTION_HOUSE_NUM, DISTRIBUTION_DIRECTION, DISTRIBUTION_STREET_NUM, DISTRIBUTION_TIME1, DISTRIBUTION_TIME2);
        BufferedReader reader = new BufferedReader(new FileReader("RandomAddresses.txt"));
        String currentLine;
        String[] line;
        while((currentLine = reader.readLine()) != null) {
            line = currentLine.split(" ");
            Address houseLocation = new Address(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), line[4], line[5]);
            totalDistance += houseLocation.calculateDistanceFromTruck(truckLocation);
            truckLocation = houseLocation;

        }

        totalDistance += dist.calculateDistanceFromTruck(truckLocation);
        reader.close();

        return totalDistance;
    }



    @Override
    public String toString() {
        return houseNumber + " " +
                direction + " " + streetNumber + " St., " + deliveryTime + " " + deliveryAMorPM;
    }

    @Override
    public int compareTo(Address address) {

        /// Following code will get both the hour and minutes from this and address.

        String[] array = this.deliveryTime.split(":");
        // getting thisHour
        int thisHour = Integer.parseInt(array[0]);
        /// converting thisHour to military time for comparison
        if (thisHour != 10 && thisHour != 11 && thisHour != 12)
        {
            thisHour += 12;
        }

        int thisMinute = Integer.parseInt(array[1]);

        String thisTime = "" + thisHour + String.format("%02d", thisMinute);
        //System.out.println(thisTime);

        String[] addressArray = address.deliveryTime.split(":");
        int addressHour = Integer.parseInt(addressArray[0]);
        // converting to address military time for comparison
        if (addressHour != 10 && addressHour != 11 && addressHour != 12)
        {
            addressHour += 12;
        }
        int addressMinute = Integer.parseInt(addressArray[1]);

        String addressTime = "" + addressHour + String.format("%02d", addressMinute);
        //System.out.println(addressTime);


        if (Integer.parseInt(thisTime) < Integer.parseInt(addressTime))
        {
            return -1;
        }
        else if (Integer.parseInt(thisTime) > Integer.parseInt(addressTime))
        {
            return 1;
        }
        return 0;
    }
}

