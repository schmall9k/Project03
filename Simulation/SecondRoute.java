package Simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SecondRoute implements Route{

    public ArrayList<Address> listOfTruckLocations = new ArrayList<>();
    public int                routeLength          = 0;


    private void handleUTurn(Address truckLocation, Address houseLocation, String directionOfTravel){
        int streetNum = truckLocation.getStreetNumber();
        int tempHouseNum = truckLocation.getHouseNumber();

        if (directionOfTravel.equals("up")){
            while (tempHouseNum % 100 != 0){
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, "", ""));
            }


            // switch direction
            String switchDirection;
            if (truckLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            if (streetNum < houseLocation.getStreetNumber()){
                tempHouseNum -= 10;
                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, "", ""));
                while (tempHouseNum % 100 != 0){
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, "", ""));
                }
            }

            else{
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, "", ""));
                while (tempHouseNum % 100 != 0){
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, streetNum, "", ""));
                }
            }

            //go back down
            tempHouseNum += 10;
            listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, "", ""));
            while (tempHouseNum % 100 != 0){
                tempHouseNum += 10;
                listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), streetNum, "", ""));
            }



        }

        else if (directionOfTravel.equals("down")){
        }

        else if (directionOfTravel.equals("right")){}

        else{}
    }


    public String getDirection(Address truck, Address houseLocation) {
        String directionTraveling = "";
        String direction = truck.getDirection();
        int houseNumber = truck.getHouseNumber();

        if (direction.equals("South") && houseLocation.getDirection().equals("South"))
        {
            if (houseNumber > houseLocation.getHouseNumber())
            {
                directionTraveling = "up";
            }
            else {
                directionTraveling = "down";
            }
        }
        else if (direction.equals("East") && houseLocation.getDirection().equals("East"))
        {
            if (houseNumber > houseLocation.getHouseNumber())
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

        return directionTraveling;
    }



    @Override
    public void calculateRoute(Address truckLocation, Address houseLocation) {

        boolean sameDirection = truckLocation.getDirection().equals(houseLocation.getDirection());
        boolean sameStreetNumber = truckLocation.getStreetNumber() == houseLocation.getStreetNumber();

        // If truck and delivery location going in same direction and on the same street, just different house number
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

        // If the truck and delivery location are on different streets
        if (sameDirection && !sameStreetNumber) {
            int tempStreetNum = truckLocation.getStreetNumber();
            int tempHouseNum  = truckLocation.getHouseNumber();
            int closestBlock  = truckLocation.getStreetNumber() * 100;
            int locationBlock = houseLocation.getStreetNumber() * 100;
            int temp2 = tempHouseNum;

            // get switch direction variable
            String switchDirection;
            if (houseLocation.getDirection().equals("South"))
                switchDirection = "East";
            else
                switchDirection = "South";

            while (tempHouseNum != closestBlock) {
                if (tempHouseNum < closestBlock) {
                    temp2 = tempHouseNum;
                    tempHouseNum += 10;

                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    temp2 = tempHouseNum;
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }

                while (tempStreetNum != houseLocation.getStreetNumber()) {
                    if (tempHouseNum % 100 == 0) {
                        while (tempHouseNum != locationBlock) {
                            if (tempHouseNum < locationBlock) {
                                temp2 = tempHouseNum;
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, switchDirection, tempStreetNum, "", ""));
                            } else {
                                temp2 = tempHouseNum = tempHouseNum;
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

                if (getDirection(new Address(temp2, switchDirection, tempStreetNum, "", ""), new Address(tempHouseNum, switchDirection, tempStreetNum, "", "")) == "up"){
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum < houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                        }
                        else  {
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum + 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                            }
                            tempHouseNum = closestBlock;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                            }
                        }
                    }

                }

                if (getDirection(new Address(temp2, switchDirection, tempStreetNum, "", ""), new Address(tempHouseNum, switchDirection, tempStreetNum, "", "")) == "down"){
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum > houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                        }
                        else  {
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                            }
                            tempHouseNum = closestBlock;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                            }
                        }
                    }

                }
                if (getDirection(new Address(temp2, switchDirection, tempStreetNum, "", ""), new Address(tempHouseNum, switchDirection, tempStreetNum, "", "")) == "right"){
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum > houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                        }
                        else  {
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                            }
                            tempHouseNum = closestBlock;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                            }
                        }
                    }

                }
                if (getDirection(new Address(temp2, switchDirection, tempStreetNum, "", ""), new Address(tempHouseNum, switchDirection, tempStreetNum, "", "")) == "left"){
                    while (tempHouseNum != houseLocation.getHouseNumber()) {
                        if (tempHouseNum < houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                        }
                        else  {
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum += 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                            }
                            for(int i = 0; i < 10; i++) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                            }
                            tempHouseNum = closestBlock;
                            while (tempHouseNum != houseLocation.getHouseNumber()) {
                                tempHouseNum -= 10;
                                listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                            }
                        }
                    }

                }
            }
        }


        // directions are different, street number is the same
        if (!sameDirection && sameStreetNumber){
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getHouseNumber();
            int closestBlock = truckLocation.getStreetNumber() * 100;
            int temp2 = tempHouseNum;
            while (closestBlock != tempHouseNum){

                if (tempHouseNum < closestBlock){
                    temp2 = tempHouseNum;
                    tempHouseNum+=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
                else{
                    temp2 = tempHouseNum;
                    tempHouseNum-=10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), truckLocation.getStreetNumber(),"",""));
                }
            }

            // switching direction


            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "up"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum + 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        tempHouseNum = closestBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "down"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        tempHouseNum = closestBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "right"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        tempHouseNum = closestBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "left"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        tempHouseNum = closestBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }

        }


        // different directions and different street numbers
        if (!sameDirection && !sameStreetNumber) {
            int locationBlock = houseLocation.getStreetNumber() * 100;
            //int thisBlock     = truckLocation.getStreetNumber() * 100;
            int tempHouseNum = truckLocation.getHouseNumber();
            int tempStreetNum = truckLocation.getStreetNumber();
            int temp2 = tempHouseNum;

            while (tempHouseNum != locationBlock) {
                if (tempHouseNum < locationBlock) {
                    temp2 = tempHouseNum;
                    tempHouseNum += 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                } else {
                    temp2 = tempHouseNum;
                    tempHouseNum -= 10;
                    listOfTruckLocations.add(new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", ""));
                }
            }

            // switch direction

            String switchDirection = houseLocation.getDirection();
            tempHouseNum = truckLocation.getStreetNumber() * 100;

            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "up"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum + 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum + 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        tempHouseNum = locationBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "down"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        tempHouseNum = locationBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "right"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum > houseLocation.getHouseNumber()) {
                        tempHouseNum -= 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        tempHouseNum = locationBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

                        }
                    }
                }

            }
            if (getDirection(new Address(temp2, truckLocation.getDirection(), tempStreetNum, "", ""), new Address(tempHouseNum, truckLocation.getDirection(), tempStreetNum, "", "")) == "left"){
                while (tempHouseNum != houseLocation.getHouseNumber()) {
                    if (tempHouseNum < houseLocation.getHouseNumber()) {
                        tempHouseNum += 10;
                        listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));

                    }
                    else  {
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum,"South", tempStreetNum - 1, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum += 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "East", tempStreetNum, "", ""));
                        }
                        for(int i = 0; i < 10; i++) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, "South", tempStreetNum, "", ""));
                        }
                        tempHouseNum = locationBlock;
                        while (tempHouseNum != houseLocation.getHouseNumber()) {
                            tempHouseNum -= 10;
                            listOfTruckLocations.add(new Address(tempHouseNum, houseLocation.getDirection(), tempStreetNum, "", ""));

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
    public void clearListOfLocations(){
        listOfTruckLocations.clear();
    }

    @Override
    public int costEffectivenessOfRoute(ArrayList<Address> route, PriorityQueue<Address> queueOfAddresses)
    {
        int cost = 0;

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
            cost += 1;

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
                        cost += 2;
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += 4;
                        directionTraveling = "right";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += 4;
                        directionTraveling = "down";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += 2;
                        directionTraveling = "down";
                    }
                }
                // else if route.get(i).houseNumber < streetNumber
                else {
                    if (directionTraveling.equals("up")) {
                        // traveling up, turn right
                        cost += 4;
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("down")) {
                        // traveling down, turn left
                        cost += 2;
                        directionTraveling = "left";
                    }
                    if (directionTraveling.equals("left")) {
                        // traveling left, turn left
                        cost += 2;
                        directionTraveling = "up";
                    }
                    if (directionTraveling.equals("right")) {
                        //traveling right, turning right
                        cost += 4;
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
        cost = cost + 5;

        // if time < 5, need enough time to make order. Order takes 5 units of time to make.
        // Can make food while truck is moving. Must not include the 5 units of time it takes to stop at each
        // delivery
        if ((cost-5) < 5)
        {
            while ((cost-5) < 5)
            {
                cost++;
            }
        }
        return cost;
    }

    @Override
    public int getRouteLength() {
        return routeLength;
    }
}
