package ru.aston.hometask2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppConfigTest {

    @Test
    void envVariable_hasHighestPriority() {
        AppConfig config = new AppConfig(
                () -> "/env/path.txt",
                () -> "/sys/path.txt"
        );

        assertEquals("/env/path.txt", config.getFilePath());
        assertTrue(config.getSource().contains("переменной окружения"));
    }

    @Test
    void sysProperty_usedWhenEnvIsEmpty() {
        AppConfig config = new AppConfig(
                () -> null,
                () -> "/sys/path.txt"
        );

        assertEquals("/sys/path.txt", config.getFilePath());
        assertTrue(config.getSource().contains("системного свойства"));
    }

    @Test
    void sysProperty_usedWhenEnvIsBlank() {
        AppConfig config = new AppConfig(
                () -> "   ",
                () -> "/sys/path.txt"
        );

        assertEquals("/sys/path.txt", config.getFilePath());
    }

    @Test
    void defaultPath_usedWhenBothAreEmpty() {
        AppConfig config = new AppConfig(
                () -> null,
                () -> null
        );

        assertEquals("students_data.txt", config.getFilePath());
        assertTrue(config.getSource().contains("по умолчанию"));
    }

    @Test
    void defaultPath_usedWhenBothAreBlank() {
        AppConfig config = new AppConfig(
                () -> "",
                () -> "   "
        );

        assertEquals("students_data.txt", config.getFilePath());
    }
}