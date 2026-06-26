package ru.aston.adapter;

public record PaymentRequest(double amountUsd, String currency, String description) {}
