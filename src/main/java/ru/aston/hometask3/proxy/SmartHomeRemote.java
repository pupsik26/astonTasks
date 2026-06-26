package ru.aston.hometask3.proxy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

final class SmartHomeRemote implements SmartDevice {
    private final SmartDevice realDevice;
    private final String deviceName;
    private final Map<String, String> logs = new HashMap<>();
    private long lastCommandTime = 0;
    private final long minIntervalMs = 1000;

    public SmartHomeRemote(SmartDevice realDevice, String deviceName) {
        this.realDevice = realDevice;
        this.deviceName = deviceName;
    }

    @Override
    public void turnOn(User user) {
        executeWithControl("ВКЛ", user, () -> realDevice.turnOn(user));
    }

    @Override
    public void turnOff(User user) {
        if (deviceName.equals("Сигнализация") && !user.isOwner()) {
            log(user, "❌ ОТКАЗ: только владелец может выключить сигнализацию");
            throw new SecurityException("Только владелец может выключить сигнализацию");
        }

        executeWithControl("ВЫКЛ", user, () -> realDevice.turnOff(user));
    }

    @Override
    public String getStatus() {
        return deviceName + " → " + realDevice.getStatus();
    }

    private void executeWithControl(String action, User user, Runnable command) {
        long now = System.currentTimeMillis();
        if (now - lastCommandTime < minIntervalMs) {
            log(user, "⚠️  ОТКАЗ: слишком частые команды (rate limit)");
            throw new RuntimeException("Подождите 1 секунду между командами");
        }

        log(user, "▶️  Команда: " + action);
        command.run();
        lastCommandTime = now;
    }

    private void log(User user, String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String logEntry = "[" + time + "] " + user.name() + ": " + message;
        logs.put(user.name() + "_" + logs.size(), logEntry);
        System.out.println("  [PROXY] " + logEntry);
    }

    public void printLogs() {
        System.out.println("\n📜 Журнал действий:");
        logs.values().forEach(System.out::println);
    }
}
