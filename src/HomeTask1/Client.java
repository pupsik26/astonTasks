package HomeTask1;

import java.math.BigDecimal;

final public class Client {
    private final String name;
    private final BankAccount account;

    public Client(final String name, final BankAccount account) {
        if (name == null || account == null) {
            throw new IllegalArgumentException("Имя или аккаунт не может быть null");
        }
        this.name = name;
        this.account = BankAccount.copyOf(account);
    }

    public String getName() {
        return name;
    }

    public BankAccount getAccount() {
        return BankAccount.copyOf(this.account);
    }

    // ==========================================
    // Насколько это норм практика давать возможность в имутабельном классе, как будто дать
    // возможность изменить (с учетом копирования) состояние, хоть и не того же самого объекта?
    // Крч патерн Wither
    // ==========================================
    public Client withBalance(final BigDecimal newBalance) {
        if (newBalance == null) {
            throw new IllegalArgumentException("New balance cannot be null");
        }

        BankAccount newAccount = new BankAccount(newBalance);
        return new Client(this.name, newAccount);
    }

    @Override
    public String toString() {
        return "HomeTask1.Client{name='" + name + "', account=" + account + "}";
    }
}
