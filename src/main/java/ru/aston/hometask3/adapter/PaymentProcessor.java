package ru.aston.hometask3.adapter;

public interface PaymentProcessor {
    PaymentResult processPayment(PaymentRequest request);
    String getProviderName();
}
