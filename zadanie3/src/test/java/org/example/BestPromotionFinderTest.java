package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BestPromotionFinderTest {
    private final BestPromotionFinder finder = new BestPromotionFinder();

    // sprawdza najlepsza kolejnosc promocji
    @Test
    void shouldFindBestPromotionOrder() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );
        List<Promotion> promotions = List.of(
                new OrderValueDiscountPromotion(300, 0.05),
                new BuyThreePayTwoPromotion(),
                new ProductCouponPromotion("P1", 0.30)
        );

        PromotionResult result = finder.findBestResult(products, promotions);

        assertEquals(475, result.getTotalToPay(), 0.001);
    }

    // sprawdza wybor z gratisem przy remisie ceny
    @Test
    void shouldChooseGiftWhenPriceIsTheSame() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 250)
        );
        List<Promotion> promotions = List.of(
                new MugGiftPromotion(200)
        );

        PromotionResult result = finder.findBestResult(products, promotions);

        assertEquals(250, result.getTotalToPay());
        assertTrue(result.hasGiftWithCode(MugGiftPromotion.MUG_CODE));
    }

    // sprawdza brak promocji w szukaniu
    @Test
    void shouldHandleEmptyPromotionList() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500)
        );

        PromotionResult result = finder.findBestResult(products, List.of());

        assertEquals(500, result.getTotalToPay());
    }
}
