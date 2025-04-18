package fastfood.domain.port.secondary;

import fastfood.domain.model.SupplyOrder;

public interface SupplierNotificationService {
    void notifyOrderSent(String supplierId, SupplyOrder order);
    void notifyOrderConfirmed(String supplierId, SupplyOrder order);
}