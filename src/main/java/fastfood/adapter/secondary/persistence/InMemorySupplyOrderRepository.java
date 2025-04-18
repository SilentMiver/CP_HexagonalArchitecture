package fastfood.adapter.secondary.persistence;

import fastfood.domain.model.SupplyOrder;
import fastfood.domain.port.secondary.SupplyOrderRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemorySupplyOrderRepository implements SupplyOrderRepository {
    private final Map<String, SupplyOrder> orders = new HashMap<>();

    @Override
    public void save(SupplyOrder order) {
        orders.put(order.getId(), order);
    }

    @Override
    public Optional<SupplyOrder> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}