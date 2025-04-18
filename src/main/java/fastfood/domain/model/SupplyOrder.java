package fastfood.domain.model;

import java.util.List;
import java.util.UUID;

public class SupplyOrder {
    private String id;
    private String supplierId;
    private List<Product> products;
    private SupplyOrderStatus status;

    public SupplyOrder(String supplierId, List<Product> products) {
        this.id = UUID.randomUUID().toString();
        this.supplierId = supplierId;
        this.products = products;
        this.status = SupplyOrderStatus.CREATED;
    }

    public String getId() { return id; }
    public String getSupplierId() { return supplierId; }
    public List<Product> getProducts() { return List.copyOf(products); }
    public SupplyOrderStatus getStatus() { return status; }

    public void send() {
        if (status != SupplyOrderStatus.CREATED) {
            throw new IllegalStateException("Order can only be sent if it is created");
        }
        status = SupplyOrderStatus.SENT;
    }

    public void confirm() {
        if (status != SupplyOrderStatus.SENT) {
            throw new IllegalStateException("Order can only be confirmed if it is sent");
        }
        status = SupplyOrderStatus.CONFIRMED;
    }

    public void setInTransit() {
        if (status != SupplyOrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be confirmed to be in transit");
        }
        status = SupplyOrderStatus.IN_TRANSIT;
    }

    public void deliver() {
        if (status != SupplyOrderStatus.IN_TRANSIT) {
            throw new IllegalStateException("Order must be in transit to be delivered");
        }
        status = SupplyOrderStatus.DELIVERED;
    }

    public void returnOrder() {
        if (status != SupplyOrderStatus.DELIVERED) {
            throw new IllegalStateException("Order must be delivered to be returned");
        }
        status = SupplyOrderStatus.RETURNED;
    }
}