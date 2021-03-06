/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Concrete decorator in the Decorator Design Pattern.

*/

// Elijah wrote this class.

package Simulation;

public class Turkey extends ContentDecorator {

    Sandwich sandwich;

    public Turkey(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Turkey";
    }

    public double cost() {
        return 1.25 + sandwich.cost();
    }

    public int seconds() { return 30 + sandwich.seconds();}
}
