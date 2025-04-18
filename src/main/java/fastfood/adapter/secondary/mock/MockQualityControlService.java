package fastfood.adapter.secondary.mock;

import fastfood.domain.model.Product;
import fastfood.domain.port.secondary.QualityControlService;

import java.util.List;

public class MockQualityControlService implements QualityControlService {
    @Override
    public boolean checkQuality(List<Product> products) {
        return true;
    }
}