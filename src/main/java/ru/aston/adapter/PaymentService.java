package ru.aston.adapter;

public class PaymentService {
    private final PaymentProcessor processor;

    public PaymentService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public void makePayment(double amountUsd, String description) {
        System.out.println("\n💳 Обработка платежа через: " + processor.getProviderName());
        System.out.println("  Сумма: " + amountUsd + " USD");
        System.out.println("  Описание: " + description);

        PaymentRequest request = new PaymentRequest(amountUsd, "USD", description);
        PaymentResult result = processor.processPayment(request);

        if (result.success()) {
            System.out.println("  ✅ Успешно! ID: " + result.transactionId());
        } else {
            System.out.println("  ❌ Ошибка: " + result.message());
        }
    }
}
