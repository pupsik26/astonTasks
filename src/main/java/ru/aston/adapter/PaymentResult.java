package ru.aston.adapter;

public record PaymentResult(boolean success, String transactionId, double amountUsd, String message) {}
