package ru.aston.hometask3.decorator;

final class EmailNotifier implements Notifier {
    private final String email;

    public EmailNotifier(String email) {
        this.email = email;
    }

    @Override
    public void send(String message) {
        System.out.println("  📧 [EmailNotifier] Отправка на " + email + ": " + message);
    }
}
