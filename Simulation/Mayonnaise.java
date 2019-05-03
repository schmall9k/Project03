package Simulation;

public class Mayonnaise extends ContentDecorator{

    Sandwich sandwich;

    public Mayonnaise(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Mayonnaise";
    }

    public double cost() {
        return .25 + sandwich.cost();
    }

    public int seconds() { return 30 + sandwich.seconds();}
}
