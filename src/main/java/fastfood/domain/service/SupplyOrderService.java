package fastfood.domain.service;

import fastfood.domain.model.Product;
import fastfood.domain.model.SupplyOrder;
import fastfood.domain.model.SupplyOrderStatus;
import fastfood.domain.port.primary.SupplyOrderManagementUseCase;
import fastfood.domain.port.secondary.ForecastService;
import fastfood.domain.port.secondary.QualityControlService;
import fastfood.domain.port.secondary.SupplierNotificationService;
import fastfood.domain.port.secondary.SupplyOrderRepository;

import java.util.List;

public class SupplyOrderService implements SupplyOrderManagementUseCase {
    private final SupplyOrderRepository repository;
    private final SupplierNotificationService notificationService;
    private final ForecastService forecastService;
    private final QualityControlService qualityControlService;

    public SupplyOrderService(
            SupplyOrderRepository repository,
            SupplierNotificationService notificationService,
            ForecastService forecastService,
            QualityControlService qualityControlService) {
        this.repository = repository;
        this.notificationService = notificationService;
        this.forecastService = forecastService;
        this.qualityControlService = qualityControlService;
    }

    @Override
    public String createSupplyOrder(String supplierId) {
        List<Product> products = forecastService.getForecast();
        return createSupplyOrder(supplierId, products);
    }

    @Override
    public String createSupplyOrder(String supplierId, List<Product> products) {
        SupplyOrder order = new SupplyOrder(supplierId, products);
        repository.save(order);
        return order.getId();
    }

    @Override
    public void sendSupplyOrder(String orderId) {
        SupplyOrder order = getOrderOrThrow(orderId);
        order.send();
        repository.save(order);
        notificationService.notifyOrderSent(order.getSupplierId(), order);
    }

    @Override
    public void confirmSupplyOrder(String orderId) {
        SupplyOrder order = getOrderOrThrow(orderId);
        order.confirm();
        repository.save(order);
        notificationService.notifyOrderConfirmed(order.getSupplierId(), order);
    }

    @Override
    public void trackSupplyOrder(String orderId, SupplyOrderStatus newStatus) {
        SupplyOrder order = getOrderOrThrow(orderId);
        switch (newStatus) {
            case IN_TRANSIT -> order.setInTransit();
            case DELIVERED -> order.deliver();
            case RETURNED -> order.returnOrder();
            default -> throw new IllegalArgumentException("Invalid status transition: " + newStatus);
        }
        repository.save(order);
    }

    @Override
    public void receiveDelivery(String orderId) {
        SupplyOrder order = getOrderOrThrow(orderId);
        order.deliver();
        if (!qualityControlService.checkQuality(order.getProducts())) {
            order.returnOrder();
        }
        repository.save(order);
    }

    @Override
    public void handleReturn(String orderId) {
        SupplyOrder order = getOrderOrThrow(orderId);
        order.returnOrder();
        repository.save(order);
    }

    @Override
    public List<Product> getForecast() {
        return forecastService.getForecast();
    }

    private SupplyOrder getOrderOrThrow(String orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }
}
