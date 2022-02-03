package com.example.dong;

import java.util.ArrayList;
import java.util.Comparator;

public class Cost {
    private ArrayList<Person> sahim = new ArrayList<>();
    private ArrayList<Person> payers = new ArrayList<>();
    public ArrayList<Person> people = new ArrayList<>();

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
        for (int i = 0; i < payers.size(); i++) {
            total += payers.get(i).getAmount();
        }

        double sahmeHarNafar = total / sahim.size();

        ArrayList<Person> sahm = new ArrayList<>();
        for (int i = 0; i < people.size(); i++) {
            if (sahim.contains(people.get(i))) {
                sahm.add(new Person(people.get(i).getName(), (sahmeHarNafar - people.get(i).getAmount())));
            } else {
                sahm.add(new Person(people.get(i).getName(), -1 * people.get(i).getAmount()));
            }
        }
        ArrayList<Person> dahande = new ArrayList<>();
        ArrayList<Person> girande = new ArrayList<>();

        for (int i = 0; i < sahm.size(); i++) {

            if (sahm.get(i).getAmount() > 0) {
                dahande.add(sahm.get(i));
            } else {
                girande.add(sahm.get(i));
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
        ArrayList<Transaction> finalTransacions = new ArrayList<>();
        for (int i = 0; i < girande.size(); i++) {
            for (int j = 0; j < dahande.size(); j++) {
                double ekhtelaf = dahande.get(j).getAmount() + girande.get(i).getAmount();
                if (ekhtelaf <= 0) {
                    finalTransacions.add(new Transaction(dahande.get(j).getAmount(), dahande.get(j), girande.get(i)));
                    girande.set(i, new Person(girande.get(i).getName(), girande.get(i).getAmount() + dahande.get(j).getAmount()));
                    dahande.set(j, new Person(dahande.get(j).getName(), 0));
                } else {
                    finalTransacions.add(new Transaction(dahande.get(j).getAmount() - ekhtelaf, dahande.get(j), girande.get(i)));
                    dahande.set(j, new Person(dahande.get(j).getName(), ekhtelaf));
                    girande.set(i, new Person(girande.get(i).getName(), 0));
                }

            }
        }
        ArrayList<Transaction> finalTransaction1 = new ArrayList<>();
        for (int i = 0; i < finalTransacions.size(); i++) {
            if (!(finalTransacions.get(i).amount == 0)) {
                finalTransaction1.add(finalTransacions.get(i));
            }
        }
        for (int i = 0; i < finalTransaction1.size(); i++) {
            System.out.println(finalTransaction1.get(i));

        }
        return finalTransaction1;
    }
}