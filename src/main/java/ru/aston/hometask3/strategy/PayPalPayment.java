package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

public final class PayPalPayment implements PaymentStrategyInterface {
    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Оплата PayPal. Сумма оплаты: " + amount + " RUB");
    }
}
