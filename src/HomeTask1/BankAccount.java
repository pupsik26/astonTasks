package HomeTask1;

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public BankAccount(BankAccount other) {
        this.balance = other.balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "HomeTask1.BankAccount{balance=" + balance + "}";
    }
}
