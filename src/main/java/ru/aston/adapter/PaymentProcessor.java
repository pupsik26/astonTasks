package ru.aston.adapter;

public interface PaymentProcessor {
    PaymentResult processPayment(PaymentRequest request);
    String getProviderName();
}
