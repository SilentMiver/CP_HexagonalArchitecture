package fastfood;

import fastfood.adapter.primary.console.ConsoleSupplyOrderManager;
import fastfood.adapter.secondary.mock.MockForecastService;
import fastfood.adapter.secondary.mock.MockQualityControlService;
import fastfood.adapter.secondary.notification.ConsoleSupplierNotificationService;
import fastfood.adapter.secondary.persistence.InMemorySupplyOrderRepository;
import fastfood.domain.port.primary.SupplyOrderManagementUseCase;
import fastfood.domain.service.SupplyOrderService;

public class FastFoodApplication {
    public static void main(String[] args) {
        var repository = new InMemorySupplyOrderRepository();
        var notificationService = new ConsoleSupplierNotificationService();
        var forecastService = new MockForecastService();
        var qualityControlService = new MockQualityControlService();

        SupplyOrderManagementUseCase useCase = new SupplyOrderService(
                repository, notificationService, forecastService, qualityControlService
        );

        var console = new ConsoleSupplyOrderManager(useCase);
        console.start();
    }
}