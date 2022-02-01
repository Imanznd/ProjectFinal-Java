package ttic;

public record Transaction(
        double amount,
        Person payer,
        Person getter
) {
    @Override
    public String toString() {
        return "Transaction{" +
                " amount=" + amount +
                ", payer=" + payer.getName() +
                ", getter=" + getter.getName() +
                " }";
    }
}
