package ru.aston.hometask2;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        AppConfig config = new AppConfig();
        ConsoleInput consoleInput = new ConsoleInput();
        FileDataService fileService = new FileDataService(config);
        StudentStreamProcessor processor = new StudentStreamProcessor();

        String filePath = config.getFilePath();

        if (!Files.exists(Paths.get(filePath))) {
            if (consoleInput.askToGenerateSampleFile(filePath)) {
                fileService.generateSampleFile();
            } else {
                consoleInput.showMessage("Отмена. Завершение работы.");
                return;
            }
        }

        fileService.processStudentsFromFile(processor::processStudents);
    }
}