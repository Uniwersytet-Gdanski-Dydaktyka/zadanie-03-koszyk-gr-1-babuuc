package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartCalculatorTest {
    private final CartCalculator calculator = new CartCalculator();

    // sprawdza sume cen
    @Test
    void shouldSumPrices() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );

        double result = calculator.sumPrices(products);

        assertEquals(700, result);
    }

    // sprawdza sume cen po promocji
    @Test
    void shouldSumDiscountPrices() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500, 300),
                new Product("P2", "mysz", 50, 0)
        );

        double result = calculator.sumDiscountPrices(products);

        assertEquals(300, result);
    }

    // sprawdza pusta liste
    @Test
    void shouldReturnZeroForEmptyList() {
        double result = calculator.sumPrices(List.of());

        assertEquals(0, result);
    }

    // sprawdza null zamiast listy
    @Test
    void shouldReturnZeroForNullList() {
        double result = calculator.sumPrices(null);

        assertEquals(0, result);
    }
}
