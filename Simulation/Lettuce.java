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
