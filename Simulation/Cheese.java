package Simulation;

public class Cheese extends ContentDecorator {

    Sandwich sandwich;

    public Cheese(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() { return sandwich.getDescription() + ", Cheese"; }

    public double cost() { return .75 + sandwich.cost(); }

    public int seconds() { return 40 + sandwich.seconds();}
}
