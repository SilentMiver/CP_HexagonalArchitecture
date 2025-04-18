package fastfood.domain.port.secondary;

import fastfood.domain.model.Product;

import java.util.List;

public interface ForecastService {
    List<Product> getForecast();
}