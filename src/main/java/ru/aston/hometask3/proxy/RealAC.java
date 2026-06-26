package ru.aston.hometask3.proxy;

public final class RealAC implements SmartDevice {
    private boolean isOn = false;
    private int temperature = 22;

    @Override
    public void turnOn(User user) {
        System.out.println("    ❄️  [RealAC] Кондиционер включён на " + temperature + "°C");
        isOn = true;
    }

    @Override
    public void turnOff(User user) {
        System.out.println("    ❄️  [RealAC] Кондиционер выключен");
        isOn = false;
    }

    @Override
    public String getStatus() {
        return "Кондиционер: " + (isOn ? "работает на " + temperature + "°C" : "выключен");
    }
}
