package fastfood.adapter.secondary.notification;

import fastfood.domain.model.SupplyOrder;
import fastfood.domain.port.secondary.SupplierNotificationService;

public class ConsoleSupplierNotificationService implements SupplierNotificationService {
    @Override
    public void notifyOrderSent(String supplierId, SupplyOrder order) {
        System.out.println("Уведомление поставщику " + supplierId + ": заказ " + order.getId() + " отправлен.");
    }

    @Override
    public void notifyOrderConfirmed(String supplierId, SupplyOrder order) {
        System.out.println("Уведомление поставщику " + supplierId + ": заказ " + order.getId() + " подтвержден.");
    }
}