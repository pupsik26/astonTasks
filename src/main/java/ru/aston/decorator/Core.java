package ru.aston.decorator;

public final class Core {
    public static void start() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("  СИСТЕМА УВЕДОМЛЕНИЙ С ДЕКОРАТОРАМИ");
        System.out.println("═══════════════════════════════════════\n");

        // 1. Простое уведомление (только Email)
        System.out.println("1️⃣  Простое уведомление (только Email):");
        Notifier simple = new EmailNotifier("user@example.com");
        simple.send("Привет!");
        System.out.println();

        // 2. Email + Логирование
        System.out.println("2️⃣  Email + Логирование:");
        LoggingDecorator withLogging = new LoggingDecorator(
                new EmailNotifier("user@example.com")
        );
        withLogging.send("Важное сообщение");
        withLogging.printLogs();
        System.out.println();

        // 3. Email + Повторные попытки
        System.out.println("3️⃣  Email + Повторные попытки (3 попытки):");
        Notifier withRetry = new RetryDecorator(
                new EmailNotifier("user@example.com"),
                3
        );
        withRetry.send("Критическое уведомление");
        System.out.println();

        // 4. Email + SMS-дублирование
        System.out.println("4️⃣  Email + SMS-дублирование:");
        Notifier withSms = new SmsBackupDecorator(
                new EmailNotifier("user@example.com"),
                "+79991234567"
        );
        withSms.send("Срочное сообщение");
        System.out.println();

        // 5. Комбинация: Email + Лог + Retry + SMS
        System.out.println("5️⃣  Комбинация всех декораторов:");
        LoggingDecorator fullFeatured = new LoggingDecorator(
                new RetryDecorator(
                        new SmsBackupDecorator(
                                new EmailNotifier("user@example.com"),
                                "+79991234567"
                        ),
                        3
                )
        );
        fullFeatured.send("Максимально надёжное уведомление");
        fullFeatured.printLogs();
    }
}
