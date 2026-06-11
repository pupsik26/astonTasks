package ru.aston.hometask1;

final class Client {
    private final String name;
    private final BankAccount account;

    public Client(String name, BankAccount account) {
        this.name = name;
        this.account = new BankAccount(account);
    }

    public String getName() {
        return name;
    }

    public BankAccount getAccount() {
        return new BankAccount(this.account);
    }

    @Override
    public String toString() {
        return "HomeTask1.Client{name='" + name + "', account=" + account + "}";
    }
}
