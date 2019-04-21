package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

// Elijah wrote bulk of this class. Kiersten wrote cost effectiveness method.
// Elijah looked at Kylie's original route to understand some logic.

public class SecondRoute implements Route {

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int routeLength = 0;
    public int speedOfTruck = 30;
    public double distanceBtwnHouses = .03;


    private void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel) {
        int streetNum = truckLocation.getStreetNumber();
        int tempHouseNum = truckLocation.getHouseNumber();

        if (directionOfTravel.equals("up")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, false));
            }


            // switch direction
            String switchDirection;
            if (truckLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            if (streetNum < houseLocation.getStreetNumber()) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, false));
                while (tempHouseNum % 100 != 0) {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, false));
                }
            } else {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, false));
                while (tempHouseNum % 100 != 0) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, false));
                }
            }

            //go back down
            tempHouseNum += 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, false));
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, false));
            }


        } else if (directionOfTravel.equals("down")) {
        } else if (directionOfTravel.equals("right")) {
        } else {
        }
    }


    public String getDirection(Address truck, Address houseLocation) {
        String directionTraveling = "";
        String direction = truck.getDirection();
        int houseNumber = truck.getHouseNumber();

        if (direction.equals("South") && houseLocation.getDirection().equals("South")) {
            if (houseNumber > houseLocation.getHouseNumber()) {
                directionTraveling = "up";
            } else {
                directionTraveling = "down";
            }
        } else if (direction.equals("East") && houseLocation.getDirection().equals("East")) {
            if (houseNumber > houseLocation.getHouseNumber()) {
                directionTraveling = "left";
            } else {
                directionTraveling = "right";
            }
        } else {
            if (direction.equals("South")) {
                directionTraveling = "up";
            } else {

                directionTraveling = "left";
            }
        }

        return directionTraveling;
    }


    public void calculateRoute(Address truckLocation, Address houseLocation) {

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        // If truck and delivery location going in same direction and on the same street, just different house number
        if (sameDirection && sameStreetNumber) {
            int tempHouseNum = truckLocation.getHouseNumber();
            while (houseLocation.getHouseNumber() != tempHouseNum) {
                if (truckLocation.getHouseNumber() < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;
                } else
                    tempHouseNum -= 10;
                Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                listOfTruckLocations.add(nextLocation);
            }
        }

        // If the truck and delivery location are on different streets
        if (sameDirection && !sameStreetNumber) {
            int tempStreetNum = truckLocation.getStreetNumber();
            int tempHouseNum = truckLocation.getHouseNumber();
            int closestBlock = truckLocation.getStreetNumber() * 100;
            int locationBlock = houseLocation.getStreetNumber() * 100;
            int previousHouse;
            int targetBlock = houseLocation.getStreetNumber() * 100;

            // get switch direction variable
            String switchDirection;
            if (houseLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            while (tempHouseNum != closestBlock) {
                if (tempHouseNum < closestBlock) {
                    previousHouse = tempHouseNum;
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                } else {
                    previousHouse = tempHouseNum;
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                }

                while (tempStreetNum != houseLocation.getStreetNumber()) {
                    if (tempHouseNum % 100 == 0) {
                        while (tempHouseNum != locationBlock) {
                            if (tempHouseNum < locationBlock) {
                                previousHouse = tempHouseNum;
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                            } else {
                                previousHouse = tempHouseNum;
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                            }
                        }
                        if (truckLocation.getStreetNumber() < houseLocation.getStreetNumber()) {
                            tempStreetNum += 1;

                        } else
                            tempStreetNum -= 1;
                    }
                }

                if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "up") {
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum < houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                        } else {
                            int newStreet;
                            int newHouse;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                            }

                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                            }
                            tempHouseNum = targetBlock;
                            newHouse = tempStreetNum * 100;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                            }
                        }
                    }

                }
                if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "down") {
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum > houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                        } else {
                            int newStreet;
                            int newHouse;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                            }

                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                            }
                            tempHouseNum = targetBlock;
                            newHouse = tempStreetNum * 100;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                            }
                        }
                    }

                }
                if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "right") {
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum > houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                        } else {
                            int newStreet;
                            int newHouse;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                            }

                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum + 1, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                            }
                            tempHouseNum = targetBlock;
                            newHouse = tempStreetNum * 100;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                            }
                        }
                    }

                }
                if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "left") {
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum < houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                        } else {
                            int newStreet;
                            int newHouse;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                            }

                            for (int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, false));
                            }
                            newStreet = tempHouseNum / 100;
                            newHouse = tempStreetNum * 100;
                            for (int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                            }
                            tempHouseNum = targetBlock;
                            newHouse = tempStreetNum * 100;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum -= 10;
                                newHouse += 10;
                                listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                            }
                        }
                    }

                }
            }
        }


        // directions are different, street number is the same
        if (!sameDirection && sameStreetNumber) {
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getHouseNumber();
            int targetBlock = houseLocation.getStreetNumber() * 100;
            int previousHouse = tempHouseNum;
            while (targetBlock != tempHouseNum) {

                if (tempHouseNum < targetBlock) {
                    previousHouse = tempHouseNum;
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false));
                } else {
                    previousHouse = tempHouseNum;
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false));
                }
            }

            // switching direction


            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "up") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "down") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "right") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum + 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "left") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                        }
                    }
                }

            }

        }


        // different directions and different street numbers
        if (!sameDirection && !sameStreetNumber) {
            int targetBlock = houseLocation.getStreetNumber() * 100;
            //int thisBlock     = truckLocation.getStreetNumber() * 100;
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getStreetNumber();
            int previousHouse = tempHouseNum;

            while (tempHouseNum != targetBlock) {
                if (tempHouseNum < targetBlock) {
                    previousHouse = tempHouseNum;
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                } else {
                    previousHouse = tempHouseNum;
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                }
            }

            // switch direction

            String switchDirection = houseLocation.getDirection();
            tempHouseNum = truckLocation.getStreetNumber() * 100;

            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "up") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "down") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "East", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "right") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum + 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                        }
                    }
                }

            }
            if (getDirection(new Address(previousHouse, truckLocation.getDirection(), tempStreetNum, false), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false)) == "left") {
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, false));

                    } else {
                        int newStreet;
                        int newHouse;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }

                        for (int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, false));
                        }
                        newStreet = tempHouseNum / 100;
                        newHouse = tempStreetNum * 100;
                        for (int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));
                        }
                        tempHouseNum = targetBlock;
                        newHouse = tempStreetNum * 100;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            newHouse += 10;
                            listOfTruckLocations.add(new Address(newHouse, "South", newStreet, false));

                        }
                    }
                }

            }
        }
    }

    @Override
    public ArrayList<Address> getListOfTruckLocations() {
        return listOfTruckLocations;
    }

    @Override
    public void clearListOfLocations() {
        listOfTruckLocations.clear();
    }

    @Override
    public double costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses) {
        double cost = 0;

        // must solve for amount of time taken to travel between each house.
        // Distance between houses = .03, speed of truck = 30mph. Can be modified up top easily
        // The answer will be in hours, will change to second for easy conversion. (3600 seconds in 1 hour) so = 3.6 seconds
        double timeInHours = distanceBtwnHouses / speedOfTruck;
        double timeUnitInSeconds = timeInHours * 3600;

        // get initial info of truck
        String direction = route.get(0).direction;
        int streetNumber = route.get(0).streetNumber;
        int houseNumber = route.get(0).houseNumber;

        String directionTraveling = "";
        // get initial direction of truck.. not South/East, but traveling up, down, left, right.
        if (direction.equals("South") && route.get(1).direction.equals("South")) {
            if (houseNumber > route.get(1).houseNumber) {
                directionTraveling = "up";
            } else {
                directionTraveling = "down";
            }
        } else if (direction.equals("East") && route.get(1).direction.equals("East")) {
            if (houseNumber > route.get(1).houseNumber) {
                directionTraveling = "left";
            } else {
                directionTraveling = "right";
            }
        } else {
            if (direction.equals("South")) {
                directionTraveling = "up";
            } else {

                directionTraveling = "left";
            }
        }

        // cycle through list of addresses that truck is traveling
        for (int i = 1; i < route.size(); i++) {

            // adding 1 for moving from one address to another, right now includes intersections.
            cost += timeUnitInSeconds;

            /* adding 5 for each stop at a delivery location
            if (queueOfAddresses.contains(route.get(i))) {
                cost = cost + 5;
            }*/

            // checking to see if direction of address is different from previous direction.
            // need to know which way truck is turning
            if (!direction.equals(route.get(i).direction)) {
                streetNumber = streetNumber * 100; // will turn 9th into 900 or 5th into 500

                if (route.get(i).houseNumber > streetNumber) {
                    if (directionTraveling.equals("up")) {
                        // traveling up, turn right
                        cost += (timeUnitInSeconds * 2);
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += (timeUnitInSeconds * 4);
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += (timeUnitInSeconds * 4);
                        directionTraveling = "down";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += (timeUnitInSeconds * 2);
                        directionTraveling = "down";
                    }
                }
                // else if route.get(i).houseNumber < streetNumber
                else {
                    if (directionTraveling.equals("up")) {
                        // traveling up, turn right
                        cost += (timeUnitInSeconds * 4);
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += (timeUnitInSeconds * 2);
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += (timeUnitInSeconds * 2);
                        directionTraveling = "up";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += (timeUnitInSeconds * 4);
                        directionTraveling = "up";
                    }
                }
            }
            direction = route.get(i).direction;
            streetNumber = route.get(i).streetNumber;
            houseNumber = route.get(i).houseNumber; // Not currently being used but updated anyway
        }

        // when you reach the last location for delivery on the route.
        // will NOT need if using priority queue
        cost = cost + (timeUnitInSeconds * 5);

        // if time < 5, need enough time to make order. Order takes 5 units of time to make.
        // Can make food while truck is moving. Must not include the 5 units of time it takes to stop at each
        // delivery
        if ((cost - (timeUnitInSeconds * 5)) < (timeUnitInSeconds * 5)) {
            while ((cost - (timeUnitInSeconds * 5)) < (timeUnitInSeconds * 5)) {
                cost += timeUnitInSeconds;
            }
        }
        return cost;
    }


    @Override
    public double getRouteLength() {
        return routeLength * distanceBtwnHouses;
    }

    @Override
    public String convertTime(double time) {
        String timeString = "";

        // finding the hours
        int hour = (int) time / 3600;

        // getting remainder to calculate both minutes and seconds
        double hourRemainder = time % 3600;

        // finding the minutes
        int minutes = (int) hourRemainder / 60;

        // getting remainder to calculate seconds
        int seconds = (int) hourRemainder % 60;

        timeString = Integer.toString(hour) + ":" + Integer.toString(minutes) + ":" + Integer.toString(seconds);

        return timeString;
    }
}
