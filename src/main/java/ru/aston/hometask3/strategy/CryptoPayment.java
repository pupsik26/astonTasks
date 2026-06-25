package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

public final class CryptoPayment implements PaymentStrategyInterface {
    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Оплата криптой. Сумма оплаты: " + amount + " RUB");
    }
}
