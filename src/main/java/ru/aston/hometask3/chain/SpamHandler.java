package ru.aston.hometask3.chain;

public final class SpamHandler extends BaseHandler {
    @Override
    protected ModerationResult check(Comment comment) {
        System.out.println("  [SpamHandler] Проверяю на спам...");
        if (comment.text().toLowerCase().contains("казино")) {
            return new ModerationResult(false, "Обнаружен спам");
        }
        return new ModerationResult(true, "");
    }
}
