package ru.aston.hometask3.adapter;

public class OldPaymentGateway {
    public String executeTransaction(String merchantId, double amountUsd, String description) {
        System.out.println("    [OldPaymentGateway] Транзакция: " + amountUsd + " USD (" + description + ")");

        // Возвращает строку формата "OK|TXN-12345|amount" или "ERROR|reason"
        if (amountUsd > 10000) {
            return "ERROR|Превышен лимит транзакции";
        }

        String txnId = "TXN-" + System.currentTimeMillis();
        return "OK|" + txnId + "|" + amountUsd;
    }

    public String getMerchantId() {
        return "MERCH-2024-001";
    }
}
