/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

A concrete decorator in the decorator pattern.
 */

// Elijah wrote this class.

package Simulation;

public class Mustard extends ContentDecorator{

    Sandwich sandwich;

    public Mustard(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Mustard";
    }

    public double cost() {
        return 1.25 + sandwich.cost();
    }

    public int seconds() { return 30 + sandwich.seconds();}
}
