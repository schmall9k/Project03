public class Address {

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
}
