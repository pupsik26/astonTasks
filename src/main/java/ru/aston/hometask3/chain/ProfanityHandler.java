package ru.aston.hometask3.chain;

public final class ProfanityHandler extends BaseHandler{
    @Override
    protected ModerationResult check(Comment comment) {
        System.out.println("  [ProfanityHandler] Проверяю на мат...");
        if (comment.text().toLowerCase().contains("дурак")) {
            return new ModerationResult(false, "Обнаружена нецензурная лексика");
        }
        return new ModerationResult(true, "");
    }
}
