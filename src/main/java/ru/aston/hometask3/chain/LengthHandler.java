package ru.aston.hometask3.chain;

public final class LengthHandler extends BaseHandler {
    @Override
    protected ModerationResult check(Comment comment) {
        System.out.println("  [LengthHandler] Проверяю длину...");
        if (comment.text().length() < 5) {
            return new ModerationResult(false, "Комментарий слишком короткий");
        }
        return new ModerationResult(true, "");
    }
}
