package ru.aston.decorator;

sealed abstract class BaseNotifierDecorator implements Notifier
        permits LoggingDecorator, RetryDecorator, SmsBackupDecorator {

    protected final Notifier wrappee;

    public BaseNotifierDecorator(Notifier wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void send(String message) {
        wrappee.send(message);
    }
}
