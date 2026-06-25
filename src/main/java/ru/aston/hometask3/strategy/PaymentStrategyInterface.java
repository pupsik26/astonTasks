package ru.aston.hometask3.strategy;

import java.math.BigDecimal;

public sealed interface PaymentStrategyInterface permits CardPayment, PayPalPayment, CryptoPayment {
    void pay(BigDecimal amount);
}
