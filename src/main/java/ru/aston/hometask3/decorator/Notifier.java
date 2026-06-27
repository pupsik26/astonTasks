package ru.aston.hometask3.decorator;

public sealed interface Notifier permits EmailNotifier, BaseNotifierDecorator {
    void send(String message);
}
