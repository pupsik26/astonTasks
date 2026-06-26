package ru.aston.decorator;

final class SmsBackupDecorator extends BaseNotifierDecorator {
    private final String phoneNumber;

    public SmsBackupDecorator(Notifier wrappee, String phoneNumber) {
        super(wrappee);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        // Сначала отправляем email (через вложенный объект)
        super.send(message);

        // Потом дублируем в SMS
        System.out.println("  📱 [SmsBackupDecorator] Дублирование в SMS на " + phoneNumber + ": " + message);
    }
}
