package ru.aston.hometask3.strategy;

public enum PaymentType {
    CARD(new CardPayment()),
    PAYPAL(new PayPalPayment()),
    CRYPTO(new CryptoPayment());

    private final PaymentStrategyInterface strategy;

    PaymentType(PaymentStrategyInterface strategy) {
        this.strategy = strategy;
    }

    public PaymentStrategyInterface getStrategy() {
        return strategy;
    }

    public static PaymentType fromInt(int numberPay) {
        return switch (numberPay) {
            case 1 -> CARD;
            case 2 -> PAYPAL;
            case 3 -> CRYPTO;
            default -> throw new IllegalArgumentException("Неизвестный способ оплаты");
        };
    }
}
