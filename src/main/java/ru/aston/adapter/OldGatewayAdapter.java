package ru.aston.adapter;

public final class OldGatewayAdapter implements PaymentProcessor {
    private final OldPaymentGateway oldGateway;

    public OldGatewayAdapter(OldPaymentGateway oldGateway) {
        this.oldGateway = oldGateway;
    }

    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("  [OldGatewayAdapter] Адаптация запроса...");

        // 1. ВЫЗОВ СТАРОГО API с другими параметрами
        String merchantId = oldGateway.getMerchantId();
        String rawResult = oldGateway.executeTransaction(
                merchantId,
                request.amountUsd(),
                request.description()
        );

        // 2. ПАРСИНГ СТРОКИ (String → PaymentResult)
        return parseResult(rawResult, request.amountUsd());
    }

    // Адаптация результата: разбор строки в объект
    private PaymentResult parseResult(String rawResult, double amountUsd) {
        String[] parts = rawResult.split("\\|");

        if ("OK".equals(parts[0])) {
            return new PaymentResult(
                    true,
                    parts[1],
                    amountUsd,
                    "Платёж успешно проведён через OldPaymentGateway"
            );
        } else {
            return new PaymentResult(
                    false,
                    null,
                    amountUsd,
                    "Ошибка: " + parts[1]
            );
        }
    }

    @Override
    public String getProviderName() {
        return "Old Payment Gateway (адаптирован)";
    }
}
