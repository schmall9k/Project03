/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Concrete decorator used in the Decorator Design Pattern.

*/

// Elijah wrote this class.

package Simulation;

public class Tomato extends ContentDecorator {

    Sandwich sandwich;

    public Tomato(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Tomato";
    }

    public double cost() { return .75 + sandwich.cost(); }

    public int seconds() { return 35 + sandwich.seconds();}
}
