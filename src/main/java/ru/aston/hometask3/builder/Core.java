package ru.aston.hometask3.builder;

public class Core {
    public static void start() {
        System.out.println("=== Пример 1: Простой GET запрос ===");
        HttpRequest getRequest = new HttpRequest.Builder("https://api.example.com/users")
                .build();
        System.out.println(getRequest);

        System.out.println("\n=== Пример 2: GET с заголовками и timeout ===");
        HttpRequest getRequestWithHeaders = new HttpRequest.Builder("https://api.example.com/users")
                .header("Authorization", "Bearer token123")
                .header("Accept", "application/json")
                .timeout(10000)
                .build();
        System.out.println(getRequestWithHeaders);

        System.out.println("\n=== Пример 3: POST запрос с body ===");
        HttpRequest postRequest = new HttpRequest.Builder("https://api.example.com/users")
                .method("POST")
                .header("Content-Type", "application/json")
                .body("{\"name\": \"John\", \"age\": 30}")
                .timeout(15000)
                .followRedirects(false)
                .build();
        System.out.println(postRequest);

        System.out.println("\n=== Пример 4: Ошибка валидации ===");
        try {
            HttpRequest invalidRequest = new HttpRequest.Builder("https://api.example.com/users")
                    .method("POST")
                    .build();
        } catch (IllegalStateException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }

        System.out.println("\n=== Пример 5: Ошибка URL ===");
        try {
            HttpRequest invalidUrl = new HttpRequest.Builder("not-a-url")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
}
