package ru.aston.decorator;

final class RetryDecorator extends BaseNotifierDecorator {
    private final int maxRetries;
    private int attempt = 0;

    public RetryDecorator(Notifier wrappee, int maxRetries) {
        super(wrappee);
        this.maxRetries = maxRetries;
    }

    @Override
    public void send(String message) {
        attempt++;
        System.out.println("  🔄 [RetryDecorator] Попытка #" + attempt);

        try {
            super.send(message);

            if (Math.random() < 0.3 && attempt < maxRetries) {
                throw new RuntimeException("Ошибка отправки");
            }

            System.out.println("  ✅ [RetryDecorator] Успешно отправлено");
            attempt = 0;
        } catch (RuntimeException e) {
            if (attempt < maxRetries) {
                System.out.println("  ❌ [RetryDecorator] Ошибка, повторяю...");
                send(message);
            } else {
                System.out.println("  💥 [RetryDecorator] Все попытки исчерпаны!");
                attempt = 0;
            }
        }
    }
}
