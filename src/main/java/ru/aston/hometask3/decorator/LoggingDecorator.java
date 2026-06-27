package ru.aston.hometask3.decorator;

import java.util.ArrayList;
import java.util.List;

final class LoggingDecorator extends BaseNotifierDecorator {
    private final List<String> logs = new ArrayList<>();

    public LoggingDecorator(Notifier wrappee) {
        super(wrappee);
    }

    @Override
    public void send(String message) {
        String logEntry = "Отправка: " + message;
        logs.add(logEntry);
        System.out.println("  📝 [LoggingDecorator] Лог: " + logEntry);

        super.send(message);
    }

    public void printLogs() {
        System.out.println("\n📜 История уведомлений:");
        logs.forEach(System.out::println);
    }
}
