package ru.aston.hometask3.proxy;

public sealed interface SmartDevice permits RealAC, RealAlarm, RealLight, SmartHomeRemote {
    void turnOn(User user);
    void turnOff(User user);
    String getStatus();
}
