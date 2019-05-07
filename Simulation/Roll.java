/*
Project03 - Sandwich Truck Simulation

Kylie Norwood, Kiersten Schmall, & Elijah Ives

Concrete component for the Decorator Pattern.
*/


// Elijah wrote this class.

package Simulation;

public class Roll extends Sandwich{

    public Roll() {description = "Roll"; }

    public double cost() { return .75; }

    public int seconds() { return 60; }
}

