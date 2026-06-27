package ru.aston.hometask3.adapter;

public record PaymentResult(boolean success, String transactionId, double amountUsd, String message) {}
