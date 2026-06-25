package ru.aston.hometask2;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public boolean askToGenerateSampleFile(String filePath) {
        System.out.printf("Файл '%s' не найден. Создать тестовый? (y/n): ", filePath);
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y") || answer.equals("yes") ||
                answer.equals("д") || answer.equals("да");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}