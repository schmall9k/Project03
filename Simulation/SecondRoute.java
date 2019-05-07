/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Extends Route class and solves for a simple route the Truck follows.
*/

package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

// Kylie wrote bulk of this class. Kiersten wrote cost effectiveness method and convert time.
// Kylie received advice and some help with logic from Elijah and Kiersten.

public class SecondRoute implements Route {

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int routeLength = 0;
    public String directionOfTravel = "";
    public int speedOfTruck = 30;
    public double distanceBtwnHouses = .03;


    // Following method solves a route the truck can travel to get to a delivery location
    // Each location truck hits is added to an ArrayList<Address>

    @Override
    public void calculateRoute(Address truckLocation, Address houseLocation) {
        listOfTruckLocations.add(truckLocation);
        routeLength++;

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        if (truckLocation == Neighborhood.DIST_CENTER) {
            directionOfTravel = "right";
        }

        int tempHouseNum = truckLocation.getHouseNumber();
        int tempStreetNum = truckLocation.getStreetNumber();
        int higherBlock = (int) (Math.ceil(tempHouseNum / 100.0) * 100);                    // ie. HN = 520, this = 600
        int lowerBlockOfHouse = (houseLocation.getHouseNumber() / 100) * 100;             // ie. HN = 520, this = 500

        // truck location and house location have same direction
        if (sameDirection) {

            // if truck and delivery location going in same direction and on the same street, just different house number
            if (sameStreetNumber) {
                // make right turn
                String switchDirection;
                if (houseLocation.getDirection().equals("South"))
                    switchDirection = "East";
                else
                    switchDirection = "South";

                if (directionOfTravel.equals("down")) {
                    int desiredBlock = (houseLocation.getHouseNumber() / 100) * 100;
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        tempHouseNum = move(tempHouseNum,houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);

                    }
                    else {
                        tempHouseNum = move(tempHouseNum, higherBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, desiredBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                    }
                }

                else if (directionOfTravel.equals("up")) {
                    int desiredBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);
                    int closestBlock = (tempHouseNum / 100) * 100;
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(),true);
                    }
                    else {
                        tempHouseNum = move(tempHouseNum, closestBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, desiredBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(),true);
                    }
                }
                else if (directionOfTravel.equals("right")) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                    else {
                        int desiredBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, higherBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, desiredBlock, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                // direction of travel = left
                else {
                    int desiredBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);
                    int closestBlock = (tempHouseNum / 100) * 100;
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                    }
                    else {
                        tempHouseNum = move(tempHouseNum, closestBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, desiredBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                }
            }

            // if truck and house same direction, but different street number, house number doesn't matter
            if (!sameStreetNumber) {
                int closestBlock = (int) (Math.ceil(tempHouseNum / 100.0) * 100);
                int desiredBlock = (houseLocation.getHouseNumber() / 100) * 100;
                if (directionOfTravel.equals("down")){

                    tempHouseNum = move(tempHouseNum, closestBlock, houseLocation.getDirection(), truckLocation.getStreetNumber(), false);

                    // make right turn
                    String switchDirection;
                    if (houseLocation.getDirection().equals("South"))
                        switchDirection = "East";
                    else
                        switchDirection = "South";

                    if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, desiredBlock, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);

                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else{
                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                }

                else if (directionOfTravel.equals("right")){
                    // make right turn
                    String switchDirection;
                    if (houseLocation.getDirection().equals("South"))
                        switchDirection = "East";
                    else
                        switchDirection = "South";

                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        while (tempHouseNum % 100 != 0){
                            tempHouseNum += 10;
                            getDirectionOftravel(houseLocation.getDirection(), false);
                            Address nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false);
                            listOfTruckLocations.add(nextLocation);
                        }

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else {
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                else if (directionOfTravel.equals("up")){
                    tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum, true);

                    // make right turn
                    String switchDirection;
                    if (houseLocation.getDirection().equals("South"))
                        switchDirection = "East";
                    else
                        switchDirection = "South";

                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        desiredBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, desiredBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, desiredBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else{
                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(),true);
                    }
                }

                // direction of travel = left
                else{
                    tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,true);

                    String switchDirection;
                    if (houseLocation.getDirection().equals("South"))
                        switchDirection = "East";
                    else
                        switchDirection = "South";

                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);
                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);
                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,switchDirection,directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;
                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(),false);
                    }

                    else{
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, switchDirection, directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, switchDirection, directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }
            }
        }

        // truck location and house location have different directions
        else {
            // directions are different, street number is the same
            if (sameStreetNumber) {
                if (directionOfTravel.equals("right")){

                    tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(),tempStreetNum,false);

                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber()){

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(),tempStreetNum,false);
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(),false);
                    }

                    else{
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                }

                else if (directionOfTravel.equals("down")){
                    tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,false);

                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber()){
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,true);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else {
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                else if (directionOfTravel.equals("up")){
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);

                    }

                    else{
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, houseLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (houseLocation.getHouseNumber() / 100 ) * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                // direction of travel = left
                else{
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),tempStreetNum,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else{
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),tempStreetNum,true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                }
            }










            // different directions and different street numbers
            if (!sameStreetNumber) {
                if (directionOfTravel.equals("right")){
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        tempHouseNum = makeRightTurn(tempHouseNum, tempStreetNum, houseLocation.getDirection(), false, false);

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;


                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock,houseLocation.getDirection(),directionChangeStreet,false);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum,getNextBlock,truckLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else{
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);
                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                }

                else if (directionOfTravel.equals("down")){
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(),directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(),directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else{
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),false);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                else if (directionOfTravel.equals("up")){
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        System.out.println("REACHED");

                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, false);


                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),truckLocation.getStreetNumber(),true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,false);

                        // switches direction (turns the truck)
                        directionChangeBlock = tempStreetNum * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,false);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(),directionChangeStreet,true);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum,getNextBlock,truckLocation.getDirection(),directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else{
                        int getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum,getNextBlock,truckLocation.getDirection(),tempStreetNum, true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                }

                // direction of travel = left
                else{
                    if (houseLocation.getHouseNumber() > truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){

                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), tempStreetNum, true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, true);
                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() > truckLocation.getStreetNumber()){
                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (houseLocation.getHouseNumber() / 100) * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, houseLocation.getDirection(), directionChangeStreet,true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;
                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), false);
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }

                    else if (houseLocation.getHouseNumber() < truckLocation.getHouseNumber() && houseLocation.getStreetNumber() < truckLocation.getStreetNumber()){
                        int getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), truckLocation.getStreetNumber(), true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }

                    else{
                        tempHouseNum = getToClosestBlock(tempHouseNum, truckLocation.getDirection(), tempStreetNum, true);

                        // switches direction (turns the truck)
                        int directionChangeBlock = tempStreetNum * 100;
                        int directionChangeStreet = tempHouseNum / 100;
                        Address nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum -= 10;
                        nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,houseLocation.getDirection(),directionChangeStreet,true);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum += 10;
                        nextLocation = new Address(tempHouseNum, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = getToClosestBlock(tempHouseNum,truckLocation.getDirection(),directionChangeStreet,false);

                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        int getNextBlock = (int) (Math.ceil(houseLocation.getHouseNumber() / 100.0) * 100);

                        tempHouseNum = move(tempHouseNum,getNextBlock,houseLocation.getDirection(),directionChangeStreet,false);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, truckLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        getNextBlock = houseLocation.getStreetNumber() * 100;

                        tempHouseNum = move(tempHouseNum, getNextBlock, truckLocation.getDirection(), directionChangeStreet, true);

                        // make right turn
                        // switches direction (turns the truck)
                        directionChangeBlock = directionChangeStreet * 100;
                        directionChangeStreet = tempHouseNum / 100;
                        nextLocation = new Address(directionChangeBlock, houseLocation.getDirection(), directionChangeStreet, false);
                        listOfTruckLocations.add(nextLocation);

                        tempHouseNum = directionChangeBlock;

                        tempHouseNum = move(tempHouseNum, houseLocation.getHouseNumber(), houseLocation.getDirection(), houseLocation.getStreetNumber(), true);
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                }
            }

            listOfTruckLocations.add(houseLocation);
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

    // The following method is for calculating the cost effectiveness of the route.
    //  - move from one address to another in 1 unit of time
    //  - a stop at a delivery address takes 5 units of time
    //  - a right hand turn takes 2 units of time
    //  - a left hand turn takes 4 units of time
    //  - time to prepare a food order is 5 units of time
    //  - compute the total length of each route in distance and time

    // Kiersten wrote this method.
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

    // Kiersten wrote this method.
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

        String hourString;
        String minutesString;
        String secondsString;

        if (hour < 10) {
            hourString = "0" + Integer.toString(hour);
        } else {
            hourString = Integer.toString(hour);
        }

        if (minutes < 10) {
            minutesString = "0" + Integer.toString(minutes);
        } else {
            minutesString = Integer.toString(minutes);
        }

        if (seconds < 10) {
            secondsString = "0" + Integer.toString(seconds);
        } else {
            secondsString = Integer.toString(seconds);
        }


        timeString = hourString + ":" + minutesString + ":" + secondsString;

        return timeString;
    }

    // method that will determine the direction the truck is currently moving.
    // Kylie wrote this method.
    private void getDirectionOftravel(String truckDirection, Boolean houseLocationDecreasing) {
        if (truckDirection.equals("South")) {
            if (houseLocationDecreasing)
                directionOfTravel = "up";
            else
                directionOfTravel = "down";
        } else {
            if (houseLocationDecreasing)
                directionOfTravel = "left";
            else
                directionOfTravel = "right";
        }
    }

    // method that will move the truck to a desired block or location on a street.
    // Kylie wrote this method.
    private int move(int tempHouseNum, int limit, String direction, int streetNumber, Boolean decreasing){
        while (tempHouseNum != limit) {
            if (decreasing)
            {tempHouseNum -= 10;
                Address nextLocation = new Address(tempHouseNum, direction, streetNumber, false);
                listOfTruckLocations.add(nextLocation);
            }

            else{
                tempHouseNum += 10;
                Address nextLocation = new Address(tempHouseNum, direction, streetNumber, false);
                listOfTruckLocations.add(nextLocation);
            }
        }

        return tempHouseNum;
    }

    // method that will make the truck handle a right turn.
    // Kylie wrote this method.
    private int makeRightTurn(int tempHouseNum, int tempStreetNum, String direction, Boolean addExtra, Boolean decreasing){

        int directionChangeBlock = tempStreetNum * 100;
        int directionChangeStreet = tempHouseNum / 100;
        Address nextLocation = new Address(directionChangeBlock, direction, directionChangeStreet, false);
        listOfTruckLocations.add(nextLocation);

        tempHouseNum = directionChangeBlock;

        if (addExtra){
            if (decreasing){
                tempHouseNum -= 10;
                nextLocation = new Address(tempHouseNum, direction, directionChangeStreet, false);
                listOfTruckLocations.add(nextLocation);
            }
            else{
                tempHouseNum += 10;
                nextLocation = new Address(tempHouseNum, direction, directionChangeStreet, false);
                listOfTruckLocations.add(nextLocation);
            }
        }
        return tempHouseNum;
    }

    // method that will move the truck to the closest block.
    // Kylie wrote this method.
    private int getToClosestBlock(int tempHouseNum, String direction, int streetNumber, Boolean decreasing){
        if (decreasing){
            while (tempHouseNum % 100 != 0){
                tempHouseNum -= 10;
                Address nextLocation = new Address(tempHouseNum, direction, streetNumber, false);
                listOfTruckLocations.add(nextLocation);
            }
        }
        else {
            while (tempHouseNum % 100 != 0){
                tempHouseNum += 10;
                Address nextLocation = new Address(tempHouseNum, direction, streetNumber, false);
                listOfTruckLocations.add(nextLocation);
            }
        }

        return tempHouseNum;
    }
}