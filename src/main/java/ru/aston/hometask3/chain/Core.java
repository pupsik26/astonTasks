package ru.aston.hometask3.chain;

import java.util.Scanner;

public class Core {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        BaseHandler chain = new SpamHandler();
        chain.setNext(new ProfanityHandler())
                .setNext(new LengthHandler());

        System.out.println("=== МОДЕРАЦИЯ КОММЕНТАРИЕВ ===\n");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) break;

            Comment comment = new Comment("user123", input);
            ModerationResult result = chain.handle(comment);

            if (result.approved()) {
                System.out.println("✅ " + result.reason());
            } else {
                System.out.println("❌ Отклонено: " + result.reason());
            }
            System.out.println();
        }
    }
}
