package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductSortServiceTest {
    private final ProductSortService service = new ProductSortService();

    // sprawdza sortowanie domyslne
    @Test
    void shouldSortByPriceDescendingAndNameAscending() {
        List<Product> products = List.of(
                new Product("P1", "mysz", 50),
                new Product("P2", "monitor", 500),
                new Product("P3", "adapter", 50)
        );

        List<Product> result = service.sortDefault(products);

        assertEquals("monitor", result.get(0).name());
        assertEquals("adapter", result.get(1).name());
        assertEquals("mysz", result.get(2).name());
    }

    // sprawdza zmiane sortowania
    @Test
    void shouldSortByNameWhenComparatorIsChanged() {
        List<Product> products = List.of(
                new Product("P1", "mysz", 50),
                new Product("P2", "monitor", 500),
                new Product("P3", "adapter", 50)
        );

        List<Product> result = service.sort(products, ProductComparators.nameAscending());

        assertEquals("adapter", result.get(0).name());
        assertEquals("monitor", result.get(1).name());
        assertEquals("mysz", result.get(2).name());
    }

    // sprawdza sortowanie pustej listy
    @Test
    void shouldReturnEmptyListForNullProducts() {
        List<Product> result = service.sortDefault(null);

        assertEquals(0, result.size());
    }
}
