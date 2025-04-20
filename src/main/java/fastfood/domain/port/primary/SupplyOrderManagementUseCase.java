package fastfood.domain.port.primary;

import fastfood.domain.model.Product;
import fastfood.domain.model.SupplyOrderStatus;

import java.util.List;

public interface SupplyOrderManagementUseCase {
    String createSupplyOrder(String supplierId); // старый метод (по-прежнему может использоваться)
    String createSupplyOrder(String supplierId, List<Product> products); // новый метод
    void sendSupplyOrder(String orderId);
    void confirmSupplyOrder(String orderId);
    void trackSupplyOrder(String orderId, SupplyOrderStatus newStatus);
    void receiveDelivery(String orderId);
    void handleReturn(String orderId);
    List<Product> getForecast();
}
