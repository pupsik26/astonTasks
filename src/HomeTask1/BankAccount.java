package HomeTask1;

import java.math.BigDecimal;

public class BankAccount {
    private BigDecimal balance;

    public BankAccount(final BigDecimal initialBalance) {
        if (initialBalance == null) {
            throw new IllegalArgumentException("Баланс равен null");
        }
        this.balance = initialBalance;
    }

    public static BankAccount copyOf(final BankAccount other) {
        if (other == null) {
            throw new IllegalArgumentException("Баланс равен null");
        }
        return new BankAccount(other.balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(final BigDecimal newBalance) {
        this.balance = newBalance;
    }

    @Override
    public String toString() {
        return "HomeTask1.BankAccount{balance=" + balance + "}";
    }
}
