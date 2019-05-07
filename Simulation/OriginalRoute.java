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

public class OriginalRoute implements Route
{

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int                routeLength          = 0;
    public String             directionOfTravel    = "";
    public int                speedOfTruck         = 30;
    public double             distanceBtwnHouses   = .03;


    private void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel, boolean sameDirection) {
        Address currentLocation = truckLocation;
        int tempStreetNum = truckLocation.getStreetNumber();
        int tempHouseNum = truckLocation.getHouseNumber();

        if (directionOfTravel.equals("up") || directionOfTravel.equals("left")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
            }

            // switch direction
            String switchDirection;
            if (truckLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            int directionChangeBlock = tempStreetNum * 100;
            int directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;

            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));

            if (sameDirection) {
                if (tempStreetNum < houseLocation.getStreetNumber()) {
                    tempHouseNum += 10;
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum += 10;
                        currentLocation = new Address(tempHouseNum, switchDirection, tempStreetNum, false);
                        listOfTruckLocations.add(currentLocation);
                    }
                }
                else {
                    tempHouseNum -= 10;
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum -= 10;
                        currentLocation = new Address(tempHouseNum, switchDirection, tempStreetNum, false);
                        listOfTruckLocations.add(currentLocation);
                        currentLocation = new Address(tempHouseNum, switchDirection, tempStreetNum, false);
                        listOfTruckLocations.add(currentLocation);
                    }
                }

            }

            else {
                if (tempHouseNum < houseLocation.getHouseNumber()) {
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum += 10;
                        currentLocation = new Address(tempHouseNum, switchDirection, tempStreetNum, false);
                        listOfTruckLocations.add(currentLocation);                    }
                } else {
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0) {
                        tempHouseNum -= 10;
                        currentLocation = new Address(tempHouseNum, switchDirection, tempStreetNum, false);
                        listOfTruckLocations.add(currentLocation);
                    }
                }
            }

            directionChangeBlock = tempStreetNum * 100;
            directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;

            currentLocation = new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false);
            listOfTruckLocations.add(currentLocation);

            //go back down
            tempHouseNum += 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                currentLocation = new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false);
                listOfTruckLocations.add(currentLocation);

            }
        }

        else if(directionOfTravel.equals("down") || directionOfTravel.equals("right")) {
            while (tempHouseNum % 100 != 0) {
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
            }

            // switch direction
            String switchDirection;
            if (truckLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            int directionChangeBlock = tempStreetNum * 100;
            int directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;

            listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));

            if (sameDirection){
                if (tempStreetNum < houseLocation.getStreetNumber()){
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    }
                }
                else{
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum,false));
                    }
                }
            }

            else{
                if (tempHouseNum < houseLocation.getHouseNumber()){
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    }
                }
                else{
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    while (tempHouseNum % 100 != 0){
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, false));
                    }
                }
            }

            directionChangeBlock = tempStreetNum * 100;
            directionChangeStreet = tempHouseNum / 100;

            tempHouseNum = directionChangeBlock;
            tempStreetNum = directionChangeStreet;


            // move back up or back left
            tempHouseNum -= 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));

            while (tempHouseNum % 100 != 0){
                tempHouseNum -= 10;
                currentLocation = new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false);
                listOfTruckLocations.add(currentLocation);
            }
        }
        calculateRoute(currentLocation, houseLocation);
    }

    /* Method that determines whether or not the truck must handle a u-turn.
       CASES: truck moving in down direction, but must move up OR truck moving in up direction, but must move down OR
       truck moving in right direction, but must move left, OR truck moving in left direction, but must move right
       *** CASES ARE SLIGHTLY DIFFERENT BASED ON TRUCK AND HOUSE DIRECTION
     */
    private Boolean needsToHandleUTurn(boolean sameDirection, int start, int destination){

        // if truck and house are in same direction
        if (sameDirection){
            if (    directionOfTravel.equals("down")  && start < destination ||
                    directionOfTravel.equals("up")    && start > destination ||
                    directionOfTravel.equals("right") && start < destination ||
                    directionOfTravel.equals("left")  && start > destination ) {
                //System.out.println("handling u turn");
                return true;
            }
        }

        // truck and house in different directions
        else if ( directionOfTravel.equals("up")    && start < destination ||
                  directionOfTravel.equals("down")  && start > destination ||
                  directionOfTravel.equals("right") && start > destination ||
                  directionOfTravel.equals("left")  && start < destination) {
            //System.out.println("handling u turn");
            return true;
        }

        return false;
    }

    // Following method solves a route the truck can travel to get to a delivery location
    // Each location truck hits is added to an ArrayList<Address>
    @Override
    public void calculateRoute(Address truckLocation, Address houseLocation) {

        listOfTruckLocations.add(truckLocation);
        routeLength++;

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        // truck location and house location have same direction
        if (sameDirection){

            // if truck and delivery location going in same direction and on the same street, just different house number
            if (sameStreetNumber){
                int tempHouseNum = truckLocation.getHouseNumber();
                if (needsToHandleUTurn(true, houseLocation.getHouseNumber(), truckLocation.getHouseNumber())) {
                    handleUTurn(truckLocation, houseLocation, directionOfTravel, true);
                    return;
                }

                while (houseLocation.getHouseNumber() != tempHouseNum){
                    if (truckLocation.getHouseNumber() < houseLocation.getHouseNumber()){
                        tempHouseNum+=10;
                        getDirectionOftravel(houseLocation.getDirection(), false);
                    }
                    else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                    Address nextLocation = new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),false);
                    listOfTruckLocations.add(nextLocation);
                    routeLength++;

                }
            }

            // if truck and house same direction, but different street number, house number doesn't matter
            if (!sameStreetNumber){
                int tempStreetNum = truckLocation.getStreetNumber();
                int tempHouseNum  = truckLocation.getHouseNumber();
                int locationBlock = houseLocation.getStreetNumber() * 100;
                int closestBlock = (tempHouseNum / 100) * 100;

                if (needsToHandleUTurn(true, closestBlock, truckLocation.getHouseNumber())) {
                    handleUTurn(truckLocation, houseLocation, directionOfTravel, true);
                    return;
                }

                while (tempHouseNum != closestBlock) {
                    if (tempHouseNum < closestBlock) {
                        tempHouseNum += 10;
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                        routeLength++;

                    } else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(truckLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                        routeLength++;
                    }
                }

                // get switch direction variable
                String switchDirection;
                if (houseLocation.getDirection().equals("South"))
                    switchDirection = "East";
                else
                    switchDirection = "South";


                // switches direction (turns the truck)
                int directionChangeBlock = tempStreetNum * 100;
                int directionChangeStreet = closestBlock / 100;

                tempHouseNum = directionChangeBlock;

                while (tempHouseNum != locationBlock){
                    if (tempHouseNum < locationBlock) {
                        tempHouseNum += 10;
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, directionChangeStreet, false));
                        routeLength++;

                    } else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(truckLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, directionChangeStreet, false));
                        routeLength++;
                    }
                }

                //change tempHouseNum back to "closestBlock", because direction has changed
                tempHouseNum = closestBlock;

                while (tempHouseNum != houseLocation.getHouseNumber()){
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        getDirectionOftravel(houseLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(), false));
                        routeLength++;

                    } else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(houseLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), houseLocation.getStreetNumber(),false));
                        routeLength++;
                    }
                }
            }
        }

        // truck location and house location have different directions
        else{
            // directions are different, street number is the same
            if (sameStreetNumber){
                int tempHouseNum = truckLocation.getHouseNumber();
                int closestBlock = truckLocation.getStreetNumber() * 100;
                if (needsToHandleUTurn(false, truckLocation.getHouseNumber(), closestBlock)) {
                    handleUTurn(truckLocation, houseLocation, directionOfTravel, false);
                    return;
                }

                while (closestBlock != tempHouseNum){
                    if (tempHouseNum < closestBlock){
                        tempHouseNum+=10;
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false));
                        routeLength++;

                    }
                    else{
                        tempHouseNum-=10;
                        getDirectionOftravel(truckLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(), false));
                        routeLength++;

                    }
                }

                // switching direction

                while (tempHouseNum != houseLocation.getHouseNumber()){
                    if (tempHouseNum < houseLocation.getHouseNumber()){
                        tempHouseNum+=10;
                        getDirectionOftravel(houseLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),false));
                        routeLength++;

                    }
                    else{
                        tempHouseNum-=10;
                        getDirectionOftravel(houseLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), truckLocation.getStreetNumber(),false));
                        routeLength++;

                    }
                }
            }

            // different directions and different street numbers
            if (!sameStreetNumber){
                int locationBlock = houseLocation.getStreetNumber() * 100;
                //int thisBlock     = truckLocation.getStreetNumber() * 100;
                int tempHouseNum = truckLocation.getHouseNumber();
                int tempStreetNum = truckLocation.getStreetNumber();
                if (needsToHandleUTurn(false, truckLocation.getHouseNumber(), locationBlock)) {
                    handleUTurn(truckLocation, houseLocation, directionOfTravel, false);
                    return;
                }

                while (tempHouseNum != locationBlock) {
                    if (tempHouseNum < locationBlock) {
                        tempHouseNum += 10;
                        getDirectionOftravel(truckLocation.getDirection(), false);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                        routeLength++;

                    } else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(truckLocation.getDirection(), true);
                        listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, false));
                        routeLength++;

                    }
                }

                // switch direction

                String switchDirection = houseLocation.getDirection();
                tempHouseNum = truckLocation.getStreetNumber() * 100;

                while (houseLocation.getHouseNumber() != tempHouseNum) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        getDirectionOftravel(houseLocation.getDirection(),false);

                    } else {
                        tempHouseNum -= 10;
                        getDirectionOftravel(houseLocation.getDirection(), true);
                    }
                    Address nextLocation = new Address(tempHouseNum, switchDirection, houseLocation.getStreetNumber(), false);
                    listOfTruckLocations.add(nextLocation);
                    routeLength++;
                }
            }
            listOfTruckLocations.add(houseLocation);
        }
    }

    private void getDirectionOftravel(String truckDirection, Boolean houseLocationDecreasing){
        if (truckDirection.equals("South")){
            if (houseLocationDecreasing)
                directionOfTravel = "up";
            else
                directionOfTravel = "down";
        }
        else{
            if (houseLocationDecreasing)
                directionOfTravel = "left";
            else
                directionOfTravel = "right";
        }
    }

    @Override
    public ArrayList<Address> getListOfTruckLocations() {
        return listOfTruckLocations;
    }

    @Override
    public void clearListOfLocations(){
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
    public double costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses)
    {
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
        if (direction.equals("South") && route.get(1).direction.equals("South"))
        {
            if (houseNumber > route.get(1).houseNumber)
            {
                directionTraveling = "up";
            }
            else {
                directionTraveling = "down";
            }
        }
        else if (direction.equals("East") && route.get(1).direction.equals("East"))
        {
            if (houseNumber > route.get(1).houseNumber)
            {
                directionTraveling = "left";
            }
            else {
                directionTraveling = "right";
            }
        }
        else {
            if (direction.equals("South"))
            {
                directionTraveling = "up";
            }
            else {

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
        if ((cost-(timeUnitInSeconds*5)) < (timeUnitInSeconds*5))
        {
            while ((cost-(timeUnitInSeconds*5)) < (timeUnitInSeconds*5))
            {
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
    public String convertTime(double time)
    {
        String timeString = "";

// finding the hours
        int hour =  (int) time / 3600;

// getting remainder to calculate both minutes and seconds
        double hourRemainder = time % 3600;

// finding the minutes
        int minutes =  (int) hourRemainder / 60;

// getting remainder to calculate seconds
        int seconds = (int) hourRemainder % 60;

        String hourString;
        String minutesString;
        String secondsString;

        if (hour < 10)
        {
            hourString = "0" + Integer.toString(hour);
        }
        else
        {
            hourString = Integer.toString(hour);
        }

        if (minutes < 10)
        {
            minutesString = "0" + Integer.toString(minutes);
        }
        else
        {
            minutesString = Integer.toString(minutes);
        }

        if (seconds < 10)
        {
            secondsString = "0" + Integer.toString(seconds);
        }
        else {
            secondsString = Integer.toString(seconds);
        }


        timeString = hourString + ":" + minutesString + ":" + secondsString;

        return timeString;
    }
}
