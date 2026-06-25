package ru.aston.hometask3.strategy;

import java.math.BigDecimal;
import java.util.Scanner;

public class Core {
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Выберите способ оплаты:");
        System.out.println("1 - Карта");
        System.out.println("2 - PayPal");
        System.out.println("3 - Крипта");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();

        PaymentType type = PaymentType.fromInt(choice);
        cart.setPaymentStrategy(type.getStrategy());

        cart.checkout(new BigDecimal(1500));
    }
}
