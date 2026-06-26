package ru.aston.hometask3.proxy;

import java.util.Set;

public final class Core {
    public static void start() {
        SmartDevice light = new RealLight();
        SmartDevice ac = new RealAC();
        SmartDevice alarm = new RealAlarm();

        SmartHomeRemote remoteLight = new SmartHomeRemote(light, "Свет");
        SmartHomeRemote remoteAC = new SmartHomeRemote(ac, "Кондиционер");
        SmartHomeRemote remoteAlarm = new SmartHomeRemote(alarm, "Сигнализация");

        User owner = new User("Иван (владелец)", Set.of("OWNER"));
        User guest = new User("Гость Пётр", Set.of("GUEST"));

        System.out.println("═══════════════════════════════════════");
        System.out.println("  🏠 УМНЫЙ ДОМ — ПУЛЬТ УПРАВЛЕНИЯ");
        System.out.println("═══════════════════════════════════════\n");

        System.out.println("1️⃣  Владелец включает свет:");
        remoteLight.turnOn(owner);
        System.out.println("   ✅ " + remoteLight.getStatus() + "\n");

        System.out.println("2️⃣  Гость включает кондиционер:");
        remoteAC.turnOn(guest);
        System.out.println("   ✅ " + remoteAC.getStatus() + "\n");

        System.out.println("3️⃣  Гость пытается выключить сигнализацию:");
        try {
            remoteAlarm.turnOff(guest);
        } catch (SecurityException e) {
            System.out.println("   ❌ " + e.getMessage() + "\n");
        }

        System.out.println("4️⃣  Владелец выключает сигнализацию:");
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        remoteAlarm.turnOff(owner);
        System.out.println("   ✅ " + remoteAlarm.getStatus() + "\n");

        System.out.println("5️⃣  Попытка быстрой повторной команды:");
        try {
            remoteLight.turnOff(owner);
        } catch (RuntimeException e) {
            System.out.println("   ❌ " + e.getMessage() + "\n");
        }

        System.out.println("6️⃣  После паузы — повторная команда:");
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        remoteLight.turnOff(owner);
        System.out.println("   ✅ " + remoteLight.getStatus() + "\n");

        remoteLight.printLogs();
        remoteAC.printLogs();
        remoteAlarm.printLogs();
    }
}
