package fastfood.domain.model;

public enum SupplyOrderStatus {
    CREATED("Создан"),
    SENT("Отправлен"),
    CONFIRMED("Подтвержден"),
    IN_TRANSIT("В пути"),
    DELIVERED("Доставлен"),
    RETURNED("Возвращен");

    private final String description;

    SupplyOrderStatus(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}