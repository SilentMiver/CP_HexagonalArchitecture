package fastfood.domain.port.secondary;

import fastfood.domain.model.Product;

import java.util.List;

public interface QualityControlService {
    boolean checkQuality(List<Product> products);
}