/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Concrete component in the Decorator Design Pattern.

*/

// Elijah wrote this class.

package Simulation;

public class Wrap extends Sandwich {

    public Wrap() {description = "Wrap";}

    public double cost() { return .50; }

    public int seconds() { return 75; }
}
