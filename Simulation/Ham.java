package Simulation;

public class Ham extends ContentDecorator {

    Sandwich sandwich;

    public Ham(Sandwich sandwich) { this.sandwich = sandwich; }

    public String getDescription() {
        return sandwich.getDescription() + ", Ham";
    }

    public double cost() {
        return 1.50 + sandwich.cost();
    }

    public int seconds() { return 30 + sandwich.seconds();}

}
