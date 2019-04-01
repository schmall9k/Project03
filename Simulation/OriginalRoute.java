/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Extends Route class and solves for a simple route the Truck follows.
*/

package Simulation;

import java.util.ArrayList;

public class OriginalRoute extends Route
{
    OriginalRoute()
    {
    }

    // Following method solves a route the truck can travel to get to a delivery location
    // Each location truck hits is added to an ArrayList<Address>
    @Override
    public void calculateRoute(Address truckLocation, Address houseLocation) {

        listOfTruckLocations.add(truckLocation);

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        // if truck and delivery location going in same direction and on the same street, just different house number
        if (sameDirection && sameStreetNumber) {
            int tempHouseNum = truckLocation.getHouseNumber();
            while (houseLocation.getHouseNumber() != tempHouseNum){
                if (truckLocation.getHouseNumber() < houseLocation.getHouseNumber()){
                    tempHouseNum+=10;
                }
                else
                    tempHouseNum-=10;
                Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),"","");
                listOfTruckLocations.add(nextLocation);
            }
        }

        // if truck and house same direction, but different street number, house number doesn't matter
        if (sameDirection && !sameStreetNumber) {
            int tempStreetNum = truckLocation.getStreetNumber();
            int tempHouseNum  = truckLocation.getHouseNumber();
            int closestBlock  = truckLocation.getStreetNumber() * 100;
            int locationBlock = houseLocation.getStreetNumber() * 100;


            // get switch direction variable
            String switchDirection;
            if (houseLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";


            while (tempHouseNum != closestBlock) {
                if (tempHouseNum < closestBlock) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }
            }

            while (tempStreetNum != houseLocation.getStreetNumber()) {
                if (tempHouseNum % 100 == 0) {
                    while (tempHouseNum != locationBlock) {
                        if (tempHouseNum < locationBlock) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, "", ""));
                        } else {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, "", ""));
                        }
                    }
                    if (truckLocation.getStreetNumber() < houseLocation.getStreetNumber()) {
                        tempStreetNum += 1;
                    } else
                        tempStreetNum -= 1;
                }
            }

            tempHouseNum = closestBlock;
            while (tempHouseNum != houseLocation.getHouseNumber()) {
                if (tempHouseNum < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));
                }
            }
        }


        // directions are different, street number is the same
        if (!sameDirection && sameStreetNumber){
            int tempHouseNum = truckLocation.getHouseNumber();
            int closestBlock = truckLocation.getStreetNumber() * 100;
            while (closestBlock != tempHouseNum){
                if (tempHouseNum < closestBlock){
                    tempHouseNum+=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
                else{
                    tempHouseNum-=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
            }

            // switching direction

            while (tempHouseNum != houseLocation.getHouseNumber()){
                if (tempHouseNum < houseLocation.getHouseNumber()){
                    tempHouseNum+=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
                else{
                    tempHouseNum-=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
            }
        }

        // different directions and different street numbers
        if (!sameDirection && !sameStreetNumber) {
            int locationBlock = houseLocation.getStreetNumber() * 100;
            //int thisBlock     = truckLocation.getStreetNumber() * 100;
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getStreetNumber();

            while (tempHouseNum != locationBlock) {
                if (tempHouseNum < locationBlock) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }
            }

            // switch direction

            String switchDirection = houseLocation.getDirection();
            tempHouseNum = truckLocation.getStreetNumber() * 100;

            while (houseLocation.getHouseNumber() != tempHouseNum) {
                if (tempHouseNum < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;

                } else {
                    tempHouseNum -= 10;
                }
                Address nextLocation = new Address(tempHouseNum, switchDirection, houseLocation.getStreetNumber(), "", "");
                listOfTruckLocations.add(nextLocation);
            }
        }
    }
}
