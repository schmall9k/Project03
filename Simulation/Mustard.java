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
