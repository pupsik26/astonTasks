package HomeTask1;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Создание объектов ===");
        BankAccount myAccount = new BankAccount(1000.0);
        Client client = new Client("Иван", myAccount);

        System.out.println("Исходное состояние клиента: " + client);
        System.out.println("Исходное состояние myAccount: " + myAccount);

        System.out.println("\n=== Попытка атаки №1: Изменение исходного объекта ===");
        myAccount.setBalance(0.0); // "Злоумышленник" обнуляет исходный счет
        System.out.println("myAccount после изменения: " + myAccount);
        System.out.println("Клиент после изменения myAccount: " + client);
        // Баланс клиента остался 1000.0, потому что мы сделали копию в конструкторе!

        System.out.println("\n=== Попытка атаки №2: Изменение через геттер ===");
        BankAccount exposedAccount = client.getAccount(); // Получаем "якобы" внутренний счет
        exposedAccount.setBalance(0.0); // Пытаемся его обнулить

        System.out.println("exposedAccount после изменения: " + exposedAccount);
        System.out.println("Клиент после изменения exposedAccount: " + client);
        // Баланс клиента всё равно остался 1000.0, потому что геттер вернул копию!

        System.out.println("\n=== ИТОГ ===");
        System.out.println("Класс HomeTask1.Client является полностью иммутабельным и защищенным.");
    }
}