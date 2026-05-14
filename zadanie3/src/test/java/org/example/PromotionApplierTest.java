package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionApplierTest {
    private final PromotionApplier applier = new PromotionApplier();

    // sprawdza kilka promocji po kolei
    @Test
    void shouldApplyManyPromotionsInOrder() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );
        List<Promotion> promotions = List.of(
                new OrderValueDiscountPromotion(300, 0.05),
                new BuyThreePayTwoPromotion()
        );

        PromotionResult result = applier.applyPromotions(products, promotions);

        assertEquals(617.5, result.getTotalToPay(), 0.001);
    }

    // sprawdza brak promocji
    @Test
    void shouldReturnOriginalTotalWhenPromotionListIsEmpty() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500)
        );

        PromotionResult result = applier.applyPromotions(products, List.of());

        assertEquals(500, result.getTotalToPay());
    }

    // sprawdza null zamiast promocji
    @Test
    void shouldHandleNullPromotionList() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500)
        );

        PromotionResult result = applier.applyPromotions(products, null);

        assertEquals(500, result.getTotalToPay());
    }
}
