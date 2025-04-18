package fastfood.adapter.secondary.mock;

import fastfood.domain.model.Product;
import fastfood.domain.port.secondary.ForecastService;

import java.util.List;

public class MockForecastService implements ForecastService {
    @Override
    public List<Product> getForecast() {
        return List.of(
                new Product("P1", "Мясо", 100),
                new Product("P2", "Булочки", 200)
        );
    }
}