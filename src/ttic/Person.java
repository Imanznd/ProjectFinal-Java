package ttic;

public class Person {

    private String name;
    private double amount;

    public Person(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
}