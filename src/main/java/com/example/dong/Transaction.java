package com.example.dong;

/**
 * POJO for transaction
 */
public class Transaction{

    double amount;
    Person payer;
    Person getter;

    public Transaction(double amount, Person payer, Person getter) {
        this.amount = amount;
        this.payer = payer;
        this.getter = getter;
    }

    public double getAmount() {
        return amount;
    }

    public Person getPayer() {
        return payer;
    }

    public Person getGetter() {
        return getter;
    }

    public void reverse(){

        Person temp = getter;
        getter = payer;
        payer = temp;
        amount = Math.abs(amount);

    }

    @Override
    public String toString() {
        return "Transaction{" +
                " amount=" + amount +
                ", payer=" + payer.getName() +
                ", getter=" + getter.getName() +
                " }";
    }

}
