package ru.aston.hometask2;

import java.util.function.Supplier;

public class AppConfig {

    private static final String ENV_VAR_NAME = "STUDENTS_DATA_FILE";
    private static final String SYSTEM_PROPERTY_NAME = "students.data.file";
    private static final String DEFAULT_FILE_PATH = "students_data.txt";

    private final String filePath;
    private final String source;

    public AppConfig() {
        this(() -> System.getenv(ENV_VAR_NAME),
             () -> System.getProperty(SYSTEM_PROPERTY_NAME)
        );
    }

    AppConfig(Supplier<String> envSupplier, Supplier<String> sysPropSupplier) {
        Resolved resolved = resolveFilePath(envSupplier, sysPropSupplier);
        this.filePath = resolved.path;
        this.source = resolved.source;
        System.out.println(source + ": " + filePath);
    }

    private record Resolved(String path, String source) {}

    private Resolved resolveFilePath(Supplier<String> envSupplier, Supplier<String> sysPropSupplier) {
        String envPath = envSupplier.get();
        if (envPath != null && !envPath.isBlank()) {
            return new Resolved(envPath, "✅ Путь из переменной окружения");
        }

        String sysPropPath = sysPropSupplier.get();
        if (sysPropPath != null && !sysPropPath.isBlank()) {
            return new Resolved(sysPropPath, "✅ Путь из системного свойства");
        }

        return new Resolved(DEFAULT_FILE_PATH, "⚠️  Путь по умолчанию");
    }

    public String getFilePath() { return filePath; }
    public String getSource() { return source; }
}