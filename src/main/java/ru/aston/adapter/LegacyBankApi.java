package ru.aston.adapter;

public class LegacyBankApi {
    public int transferRubles(String account, double rubles, String pin) {
        System.out.println("    [LegacyBankApi] Перевод " + rubles + " RUB на счёт " + account);

        if (pin == null || pin.length() != 4) {
            throw new RuntimeException("Неверный PIN");
        }

        return (int) (Math.random() * 1_000_000); // ID транзакции
    }

    public double getExchangeRate() {
        return 95.5;
    }

    public String getAccountInfo() {
        return "Счёт: 40817810099910004321, Владелец: ООО 'Ромашка'";
    }
}
