package ru.aston.hometask3.chain;

public abstract class BaseHandler implements HandlerInterface {
    private HandlerInterface next;

    @Override
    public HandlerInterface setNext(HandlerInterface next) {
        this.next = next;
        return next;
    }

    @Override
    public ModerationResult handle(Comment comment) {
        ModerationResult result = check(comment);

        if (!result.approved()) {
            return result;
        }

        if (next != null) {
            return next.handle(comment);
        }

        return new ModerationResult(true, "Комментарий одобрен");
    }

    protected abstract ModerationResult check(Comment comment);
}
