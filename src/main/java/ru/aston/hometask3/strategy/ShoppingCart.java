package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

public class ShoppingCart {
    private PaymentStrategyInterface paymentStrategy;

    public void setPaymentStrategy(PaymentStrategyInterface strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(BigDecimal amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Способ оплаты не выбран");
        }
        paymentStrategy.pay(amount);
    }
}
