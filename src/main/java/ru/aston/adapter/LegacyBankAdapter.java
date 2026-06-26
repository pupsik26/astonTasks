package ru.aston.adapter;

public final class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankApi legacyApi;
    private final String account;
    private final String pin;

    public LegacyBankAdapter(LegacyBankApi legacyApi, String account, String pin) {
        this.legacyApi = legacyApi;
        this.account = account;
        this.pin = pin;
    }

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("  [LegacyBankAdapter] Адаптация запроса...");

        try {
            // 1. КОНВЕРТАЦИЯ ВАЛЮТЫ (USD → RUB)
            double exchangeRate = legacyApi.getExchangeRate();
            double amountRubles = request.amountUsd() * exchangeRate;
            System.out.println("    Конвертация: " + request.amountUsd() + " USD × " +
                    exchangeRate + " = " + amountRubles + " RUB");

            // 2. ВЫЗОВ СТАРОГО API с другими параметрами
            int transactionId = legacyApi.transferRubles(account, amountRubles, pin);

            // 3. АДАПТАЦИЯ РЕЗУЛЬТАТА (int → PaymentResult)
            return new PaymentResult(
                    true,
                    "BANK-" + transactionId,
                    request.amountUsd(),
                    "Платёж успешно проведён через LegacyBankApi"
            );

        } catch (RuntimeException e) {
            return new PaymentResult(false, null, request.amountUsd(), "Ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getProviderName() {
        return "Legacy Bank API (адаптирован)";
    }
}
