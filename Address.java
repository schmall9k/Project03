public class Address implements Comparable<Address>{

    public int houseNumber;
    public String direction;
    public int streetNumber;


    public Address(int houseNumber, String direction, int streetNumber) {
        this.houseNumber = houseNumber;
        this.direction = direction;
        this.streetNumber = streetNumber;
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


    @Override
    public String toString() {
        return  houseNumber + " " +
                direction + " " + streetNumber + " St.";
    }


    @Override
    public int compareTo(Address truckLocation) {
        int finalDistance = 0;
        if (this.direction.equals(truckLocation.direction))
        {
            finalDistance = truckLocation.houseNumber - this.houseNumber;
            if (finalDistance < 0)
                finalDistance = finalDistance * -1;
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

            finalDistance = distance1 + distance2;

        }
        return 0;
    }
}
