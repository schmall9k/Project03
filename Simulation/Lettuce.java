/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

A concrete decorator in the decorator pattern.
 */

// Elijah wrote this class.

package Simulation;

public class Lettuce extends ContentDecorator {

    Sandwich sandwich;

    public Lettuce(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Lettuce";
    }

    public double cost() {
        return .50 + sandwich.cost();
    }

    public int seconds() { return 20 + sandwich.seconds();}
}
