/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & ELijah Ives

Address class to represent locations of houses and the truck.

 */

package Simulation;

public class Address implements Comparable<Address> {

    public int    houseNumber;
    public String direction;
    public int    streetNumber;
    public int    distance;
    public String deliveryTime;
    public String deliveryAMorPM;


    public Address(int houseNumber, String direction, int streetNumber, String deliveryTime, String deliveryAMorPM) {
        this.houseNumber    = houseNumber;
        this.direction      = direction;
        this.streetNumber   = streetNumber;
        this.deliveryTime   = deliveryTime;
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
        return distance;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getDeliveryAMorPM() {
        return deliveryAMorPM;
    }



    //Method that will calculate how many units away a house is from a given location.
    public int calculateDistanceFromLocation(Address location)
    {
        if (this.direction.equals(location.getDirection()))
        {
            distance = Math.abs(location.getHouseNumber() - this.houseNumber);
        }

        else {
            int distance1 = 0;
            int distance2 = 0;

            if (direction == location.getDirection()) {
                distance1 = Math.abs(location.getHouseNumber() - houseNumber);
                distance2 = Math.abs((location.getStreetNumber() * 100) - (streetNumber * 100));
                distance = distance1 + distance2;
            }

            else{
                distance1 = Math.abs(location.getHouseNumber() - (streetNumber * 100));
                distance2 = Math.abs((location.getStreetNumber() * 100) - houseNumber);
                distance = distance1 + distance2;
            }

        }
        return distance;
    }


    @Override
    public String toString() {
        return houseNumber + " " +
                direction + " " + streetNumber + " St., " + deliveryTime + " " + deliveryAMorPM;
    }

    //Currently ordered by order time of the delivery
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

