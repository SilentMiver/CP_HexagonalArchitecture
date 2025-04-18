package fastfood.adapter.primary.console;

import fastfood.domain.model.Product;
import fastfood.domain.model.SupplyOrderStatus;
import fastfood.domain.port.primary.SupplyOrderManagementUseCase;

import java.util.List;
import java.util.Scanner;

public class ConsoleSupplyOrderManager {
    private final SupplyOrderManagementUseCase useCase;
    private final Scanner scanner;

    public ConsoleSupplyOrderManager(SupplyOrderManagementUseCase useCase) {
        this.useCase = useCase;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            handleChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Управление заказами поставщикам =====");
        System.out.println("1. Создать заказ поставщику");
        System.out.println("2. Отправить заказ");
        System.out.println("3. Подтвердить заказ");
        System.out.println("4. Отслеживать заказ");
        System.out.println("5. Принять поставку");
        System.out.println("6. Обработать возврат");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 0 -> System.out.println("Выход...");
            case 1 -> createOrder();
            case 2 -> sendOrder();
            case 3 -> confirmOrder();
            case 4 -> trackOrder();
            case 5 -> receiveDelivery();
            case 6 -> handleReturn();
            default -> System.out.println("Неверный выбор.");
        }
    }

    private void createOrder() {
        System.out.print("Введите ID поставщика: ");
        String supplierId = scanner.nextLine();
        String orderId = useCase.createSupplyOrder(supplierId);
        System.out.println("Заказ создан с ID: " + orderId);
    }

    private void sendOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        useCase.sendSupplyOrder(orderId);
        System.out.println("Заказ отправлен.");
    }

    private void confirmOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        useCase.confirmSupplyOrder(orderId);
        System.out.println("Заказ подтвержден.");
    }

    private void trackOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        System.out.println("Выберите статус (1 - В пути, 2 - Доставлен): ");
        int statusChoice = scanner.nextInt();
        SupplyOrderStatus status = statusChoice == 1 ? SupplyOrderStatus.IN_TRANSIT : SupplyOrderStatus.DELIVERED;
        useCase.trackSupplyOrder(orderId, status);
        System.out.println("Статус обновлен.");
    }

    private void receiveDelivery() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        useCase.receiveDelivery(orderId);
        System.out.println("Поставка принята.");
    }

    private void handleReturn() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine();
        useCase.handleReturn(orderId);
        System.out.println("Возврат обработан.");
    }
}