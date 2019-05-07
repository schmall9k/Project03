/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Sandwich is the component of the decorator pattern.

*/

// Elijah wrote this class.

package Simulation;

public abstract class Sandwich {

    String description = "Unknown Sandwich";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

    public abstract int seconds();

}
