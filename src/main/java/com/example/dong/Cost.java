package com.example.dong;

import java.util.ArrayList;
import java.util.Comparator;

public class Cost {

    private final ArrayList<Person> sahim = new ArrayList<>();
    private final ArrayList<Person> payers = new ArrayList<>();
    private final ArrayList<Person> people = new ArrayList<>();

    public void addJustPayer(Person p) {
        if (p.getAmount() > 0) {
            payers.add(p);
            people.add(p);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addPayerAndSahim(Person p) {
        sahim.add(p);
        people.add(p);
        if (p.getAmount() > 0) {
            payers.add(p);
        }
    }

    public ArrayList<Transaction> finalCalculate() {

        double total = 0;
        for (Person payer : payers) {
            total += payer.getAmount();
        }

        double sahmeHarNafar = total / sahim.size();

        ArrayList<Person> sahm = new ArrayList<>();
        for (Person person : people) {
            if (sahim.contains(person)) {
                sahm.add(new Person(person.getName(), (sahmeHarNafar - person.getAmount())));
            } else {
                sahm.add(new Person(person.getName(), -1 * person.getAmount()));
            }
        }
        ArrayList<Person> dahande = new ArrayList<>();
        ArrayList<Person> girande = new ArrayList<>();

        for (Person person : sahm) {

            if (person.getAmount() > 0) {
                dahande.add(person);
            } else {
                girande.add(person);
            }
        }
        girande.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (int) (o1.getAmount() - o2.getAmount());
            }
        });
        dahande.sort(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (int) (o2.getAmount() - o1.getAmount());
            }
        });

        ArrayList<Transaction> finalTransactions = new ArrayList<>();
        for (int i = 0; i < girande.size(); i++) {
            for (int j = 0; j < dahande.size(); j++) {
                double ekhtelaf = dahande.get(j).getAmount() + girande.get(i).getAmount();
                if (ekhtelaf <= 0) {
                    finalTransactions.add(new Transaction(dahande.get(j).getAmount(), dahande.get(j), girande.get(i)));
                    girande.set(i, new Person(girande.get(i).getName(), girande.get(i).getAmount() + dahande.get(j).getAmount()));
                    dahande.set(j, new Person(dahande.get(j).getName(), 0));
                } else {
                    finalTransactions.add(new Transaction(dahande.get(j).getAmount() - ekhtelaf, dahande.get(j), girande.get(i)));
                    dahande.set(j, new Person(dahande.get(j).getName(), ekhtelaf));
                    girande.set(i, new Person(girande.get(i).getName(), 0));
                }

            }
        }
        ArrayList<Transaction> finalTransaction1 = new ArrayList<>();
        for (Transaction finalTransaction : finalTransactions) {
            if (!(finalTransaction.amount == 0)) {
                finalTransaction1.add(finalTransaction);
            }
        }
        for (Transaction transaction : finalTransaction1) {
            System.out.println(transaction);
        }
        return finalTransaction1;
    }

    public void clear() {
        sahim.clear();
        payers.clear();
        people.clear();
    }
}