package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

public final class CardPayment implements PaymentStrategyInterface {
    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Оплата картой. Сумма оплаты: " + amount + " RUB");
    }
}
