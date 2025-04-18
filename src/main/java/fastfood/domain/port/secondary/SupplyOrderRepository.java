package fastfood.domain.port.secondary;

import fastfood.domain.model.SupplyOrder;

import java.util.Optional;

public interface SupplyOrderRepository {
    void save(SupplyOrder order);
    Optional<SupplyOrder> findById(String id);
}