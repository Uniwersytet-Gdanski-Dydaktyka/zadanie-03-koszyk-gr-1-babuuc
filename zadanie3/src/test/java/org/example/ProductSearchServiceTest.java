package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductSearchServiceTest {
    private final ProductSearchService service = new ProductSearchService();

    // sprawdza najtanszy produkt
    @Test
    void shouldFindCheapestProduct() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );

        Optional<Product> result = service.findCheapest(products);

        assertTrue(result.isPresent());
        assertEquals("mysz", result.get().name());
    }

    // sprawdza najdrozszy produkt
    @Test
    void shouldFindMostExpensiveProduct() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );

        Optional<Product> result = service.findMostExpensive(products);

        assertTrue(result.isPresent());
        assertEquals("monitor", result.get().name());
    }

    // sprawdza kilka najtanszych produktow
    @Test
    void shouldFindNCheapestProducts() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );

        List<Product> result = service.findNCheapest(products, 2);

        assertEquals(2, result.size());
        assertEquals("mysz", result.get(0).name());
        assertEquals("klawiatura", result.get(1).name());
    }

    // sprawdza kilka najdrozszych produktow
    @Test
    void shouldFindNMostExpensiveProducts() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );

        List<Product> result = service.findNMostExpensive(products, 2);

        assertEquals(2, result.size());
        assertEquals("monitor", result.get(0).name());
        assertEquals("klawiatura", result.get(1).name());
    }

    // sprawdza pusta liste
    @Test
    void shouldReturnEmptyWhenProductsAreEmpty() {
        Optional<Product> result = service.findCheapest(List.of());

        assertTrue(result.isEmpty());
    }

    // sprawdza null w liscie
    @Test
    void shouldSkipNullProducts() {
        List<Product> products = new java.util.ArrayList<>();
        products.add(null);
        products.add(new Product("P1", "monitor", 500));

        Optional<Product> result = service.findCheapest(products);

        assertTrue(result.isPresent());
        assertEquals("monitor", result.get().name());
    }
}
