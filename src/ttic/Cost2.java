package ttic;

import java.util.ArrayList;

public class Cost2 {
    public ArrayList<Person> sahim = new ArrayList<>();
    public ArrayList<Person> payers = new ArrayList<>();

    public void addJustPayer(Person p) {
        if (p.getAmount() > 0) {
            payers.add(p);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void addPayerAndSahim(Person p) {
        sahim.add(p);
        if (p.getAmount() > 0) {
            payers.add(p);
        }
    }

    public ArrayList<Transaction> calculate() {

        ArrayList<Transaction> transactions = new ArrayList<>();


        for (int i = 0; i < sahim.size(); i++) {
            for (int j = 0; j < payers.size(); j++) {
                if (!sahim.get(i).getName().equals(payers.get(j).getName())) {
                    transactions.add(
                            new Transaction(payers.get(j).getAmount() / sahim.size(),
                                    sahim.get(i),
                                    payers.get(j)
                            )
                    );
                }
            }
            for (int k = 0; k < transactions.size(); k++) {
                for (int l = 0; l < transactions.size(); l++) {

                    if (transactions.get(k).payer().getName().equals(transactions.get(l).getter().getName())
                            && transactions.get(k).getter().getName().equals(transactions.get(l).payer().getName())) {

                        double ekhtelaf = transactions.get(k).amount() - transactions.get(l).amount();
                        if (ekhtelaf == 0){
                            transactions.remove(k);
                            transactions.remove(l);

                        }
                        if (ekhtelaf > 0) {
                            transactions.set(k, new Transaction(ekhtelaf, transactions.get(k).payer(), transactions.get(k).getter()));
                            transactions.remove(l);
                        }
                        if (ekhtelaf < 0){
                            transactions.set(k, new Transaction(Math.abs(ekhtelaf), transactions.get(l).payer(), transactions.get(l).getter()));
                            transactions.remove(l);
                        }

                    }
                }
            }
        }
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
        return transactions;

    }

}
