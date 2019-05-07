/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Address class to represent locations of houses, deliveries, and the truck.

 */

package Simulation;

// Kiersten wrote this class. Kylie helped with calculateDistanceFrom(location) method.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Address implements Comparable<Address> {

    public static int   numberOrder = 1;

    public int          houseNumber;
    public String       direction;
    public int          streetNumber;
    public double       distance;
    public String       deliveryTime;
    public String       deliveryAMorPM;
    public Order        foodOrder;
    public Boolean      isDeliveryLocation;
    public double       distanceBtwnHouses = .03;

    // Kylie wrote the constructor.
    public Address(int houseNumber, String direction, int streetNumber, Boolean isDeliveryLocation) {
        this.houseNumber        = houseNumber;
        this.direction          = direction;
        this.streetNumber       = streetNumber;
        this.isDeliveryLocation = isDeliveryLocation;

        /*if (isDeliveryLocation) {
            createDeliveryTime();
            this.foodOrder = new Order();
        }*/

        if (isDeliveryLocation) {
            createDeliveryTime();
            this.foodOrder = new Order(numberOrder);
            numberOrder++;
        }

    }

    // Method that will calculate how many units away a house is from a given location.
    // Kiersten wrote this method. Asked Kylie a few questions.
    public double calculateDistanceFromLocation(Address location) {
        int houseDistance;

        //same direction
        if (this.direction.equals(location.getDirection()))
        {
            //different street number
            if (this.streetNumber != location.streetNumber)
            {
                int streetDistance = Math.abs(this.streetNumber - location.streetNumber);
                streetDistance = streetDistance * 10;


                int houseNumber1 = this.houseNumber / 10;
                int houseNumber2 = location.houseNumber / 10;

                int numberOfBlocks = 0;
                if (houseNumber1 != houseNumber2)
                {
                    houseDistance = Math.abs(houseNumber1 - houseNumber2);
                    numberOfBlocks += Math.abs(this.streetNumber - location.streetNumber) + 1;

                }
                else {
                    houseNumber1 = houseNumber1 % 10;
                    houseNumber2 = houseNumber2 % 10;

                    houseDistance = houseNumber1 + houseNumber2;
                }

                distance = (streetDistance*distanceBtwnHouses) + (houseDistance*distanceBtwnHouses) + (numberOfBlocks*distanceBtwnHouses);
            }

            //same street number
            else {
                int houseNumber1 = this.houseNumber / 10;
                int houseNumber2 = location.houseNumber / 10;

                houseDistance = Math.abs(houseNumber1 - houseNumber2);

                distance = (houseDistance*distanceBtwnHouses);
            }

        }

        //different direction
        else {

            //same street number
            if (this.streetNumber == location.streetNumber)
            {
                int thisBlock = this.streetNumber * 100;

                int distance1 = Math.abs(thisBlock - this.houseNumber) / 10;

                int distance2 = Math.abs(thisBlock - location.houseNumber) / 10;

                distance = (distance1*distanceBtwnHouses) + (distance2*distanceBtwnHouses);

            }

            //different street number
            else {

                int locationBlock = location.streetNumber * 100;
                int thisBlock = this.streetNumber * 100;

                int distance1 = Math.abs(this.houseNumber - locationBlock) / 10;
                int distance2 = Math.abs(location.houseNumber - thisBlock) / 10;

                distance = (distance1*distanceBtwnHouses) + (distance2*distanceBtwnHouses);
            }
        }
        return distance;
    }

    // Kiersten wrote this method.
    public void createDeliveryTime(){
        // generate random delivery time
        List<Integer> givenListHours = Arrays.asList(1, 2, 3, 4, 5, 6, 10, 11, 12);
        String time;
        String AMorPM; // true = AM, false = PM

        ArrayList<String> deliveryTimes = new ArrayList<>();

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

        deliveryTime = time;
        deliveryAMorPM = AMorPM;

        deliveryTimes.add(time);
    }

    // Kiersten wrote this method.
    @Override
    public String toString() {
        if (isDeliveryLocation)
            return houseNumber + " " + direction + " " + streetNumber + " St., " + deliveryTime + " " + deliveryAMorPM + " " + foodOrder.toString();
        return houseNumber + " " + direction + " " + streetNumber + " St.";

    }

    // Currently ordered based on order time of the delivery
    // Kiersten wrote this method.
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

        String[] addressArray = address.deliveryTime.split(":");
        int addressHour = Integer.parseInt(addressArray[0]);

        // converting to address military time for comparison
        if (addressHour != 10 && addressHour != 11 && addressHour != 12)
        {
            addressHour += 12;
        }
        int addressMinute = Integer.parseInt(addressArray[1]);

        String addressTime = "" + addressHour + String.format("%02d", addressMinute);

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

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getDirection() {
        return direction;
    }

    public int getStreetNumber() {
        return streetNumber;
    }
}

