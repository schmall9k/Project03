public class Address implements Comparable<Address>{

    public int houseNumber;
    public String direction;
    public int streetNumber;
    public int distanceFromTruck;


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

    public int getDistanceFromTruck() {
        return distanceFromTruck;
    }

    public void calculateDistanceFromTruck(Address truckLocation)
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
    }


    @Override
    public String toString() {
        return  houseNumber + " " +
                direction + " " + streetNumber + " St.";
    }

    @Override
    public int compareTo(Address address) {
        if (this.distanceFromTruck < address.distanceFromTruck)
            return -1;
        if (this.distanceFromTruck > distanceFromTruck)
            return 1;
        return 0;
    }
}
