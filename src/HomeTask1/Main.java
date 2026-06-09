package HomeTask1;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        final BigDecimal initialAmount = new BigDecimal("1000.50");

        final BankAccount myAccount = new BankAccount(initialAmount);
        final Client client = new Client("Иван", myAccount);

        System.out.println("Исходное состояние: " + client);

        myAccount.setBalance(new BigDecimal("0.00"));
        System.out.println("После изменения myAccount: " + client);

        final BankAccount exposedAccount = client.getAccount();
        exposedAccount.setBalance(new BigDecimal("999999.00"));
        System.out.println("После изменения exposedAccount: " + client);
    }
}