/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

A concrete decorator in the decorator pattern.

 */

// Elijah wrote this class.

package Simulation;

public class Cheese extends ContentDecorator {

    Sandwich sandwich;

    public Cheese(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() { return sandwich.getDescription() + ", Cheese"; }

    public double cost() { return .75 + sandwich.cost(); }

    public int seconds() { return 40 + sandwich.seconds();}
}
