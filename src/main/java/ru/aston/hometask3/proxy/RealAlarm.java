package ru.aston.hometask3.proxy;

final class RealAlarm implements SmartDevice {
    private boolean isActive = true;

    @Override
    public void turnOn(User user) {
        System.out.println("    🚨 [RealAlarm] Сигнализация активирована");
        isActive = true;
    }

    @Override
    public void turnOff(User user) {
        System.out.println("    🚨 [RealAlarm] Сигнализация деактивирована");
        isActive = false;
    }

    @Override
    public String getStatus() {
        return "Сигнализация: " + (isActive ? "активна" : "выключена");
    }
}
