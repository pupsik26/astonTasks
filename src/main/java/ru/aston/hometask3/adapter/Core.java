package ru.aston.hometask3.adapter;

public final class Core {
    public static void start() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("  ИНТЕГРАЦИЯ ПЛАТЁЖНЫХ СИСТЕМ");
        System.out.println("═══════════════════════════════════════");

        LegacyBankApi legacyBank = new LegacyBankApi();
        OldPaymentGateway oldGateway = new OldPaymentGateway();

        PaymentProcessor bankAdapter = new LegacyBankAdapter(legacyBank, "40817810099910004321", "1234");
        PaymentProcessor gatewayAdapter = new OldGatewayAdapter(oldGateway);

        PaymentService service = new PaymentService(bankAdapter);

        service.makePayment(100.0, "Покупка товаров");

        service = new PaymentService(gatewayAdapter);
        service.makePayment(250.0, "Подписка Premium");

        service.makePayment(15000.0, "Крупная покупка");

        System.out.println("\n═══════════════════════════════════════");
        System.out.println("  Переключение между адаптерами");
        System.out.println("═══════════════════════════════════════");

        PaymentService flexibleService = new PaymentService(bankAdapter);
        flexibleService.makePayment(50.0, "Тест 1");

        flexibleService = new PaymentService(gatewayAdapter);
        flexibleService.makePayment(75.0, "Тест 2");
    }
}
