package ru.aston.decorator;

public sealed interface Notifier permits EmailNotifier, BaseNotifierDecorator {
    void send(String message);
}
