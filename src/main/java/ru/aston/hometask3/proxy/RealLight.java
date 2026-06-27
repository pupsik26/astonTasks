package ru.aston.hometask3.proxy;

public final class RealLight implements SmartDevice {
    private boolean isOn = false;

    @Override
    public void turnOn(User user) {
        System.out.println("    💡 [RealLight] Свет включён");
        isOn = true;
    }

    @Override
    public void turnOff(User user) {
        System.out.println("    💡 [RealLight] Свет выключен");
        isOn = false;
    }

    @Override
    public String getStatus() {
        return "Свет: " + (isOn ? "включён" : "выключен");
    }
}
