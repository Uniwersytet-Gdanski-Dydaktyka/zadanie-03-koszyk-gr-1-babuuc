package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PromotionTest {

    // sprawdza rabat za wartosc koszyka
    @Test
    void shouldApplyOrderValueDiscount() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50)
        );
        Promotion promotion = new OrderValueDiscountPromotion(300, 0.05);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(522.5, result.getTotalToPay(), 0.001);
    }

    // sprawdza brak rabatu za mala wartosc
    @Test
    void shouldNotApplyOrderValueDiscountWhenTotalIsTooSmall() {
        List<Product> products = List.of(
                new Product("P1", "mysz", 50),
                new Product("P2", "kabel", 20)
        );
        Promotion promotion = new OrderValueDiscountPromotion(300, 0.05);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(70, result.getTotalToPay());
    }

    // sprawdza promocje dwa plus jeden
    @Test
    void shouldApplyBuyThreePayTwoPromotion() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50),
                new Product("P3", "klawiatura", 150)
        );
        Promotion promotion = new BuyThreePayTwoPromotion();

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(650, result.getTotalToPay());
    }

    // sprawdza promocje dwa plus jeden dla szesciu produktow
    @Test
    void shouldApplyBuyThreePayTwoPromotionTwice() {
        List<Product> products = List.of(
                new Product("P1", "a", 10),
                new Product("P2", "b", 20),
                new Product("P3", "c", 30),
                new Product("P4", "d", 40),
                new Product("P5", "e", 50),
                new Product("P6", "f", 60)
        );
        Promotion promotion = new BuyThreePayTwoPromotion();

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(180, result.getTotalToPay());
    }

    // sprawdza kubek gratis
    @Test
    void shouldAddMugGift() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500)
        );
        Promotion promotion = new MugGiftPromotion(200);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(1, result.gifts().size());
        assertTrue(result.hasGiftWithCode(MugGiftPromotion.MUG_CODE));
    }

    // sprawdza brak kubka przy malej kwocie
    @Test
    void shouldNotAddMugGiftWhenTotalIsTooSmall() {
        List<Product> products = List.of(
                new Product("P1", "mysz", 50)
        );
        Promotion promotion = new MugGiftPromotion(200);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(0, result.gifts().size());
    }

    // sprawdza kupon na jeden produkt
    @Test
    void shouldApplyProductCoupon() {
        List<Product> products = List.of(
                new Product("P1", "monitor", 500),
                new Product("P2", "mysz", 50)
        );
        Promotion promotion = new ProductCouponPromotion("P1", 0.30);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(400, result.getTotalToPay());
    }

    // sprawdza puste miejsca w koszyku
    @Test
    void shouldSkipNullProductsInPromotion() {
        List<Product> products = new ArrayList<>();
        products.add(null);
        products.add(new Product("P1", "monitor", 500));
        Promotion promotion = new OrderValueDiscountPromotion(300, 0.05);

        PromotionResult result = promotion.apply(products, List.of());

        assertEquals(475, result.getTotalToPay());
    }

    // sprawdza promocje na pustym koszyku
    @Test
    void shouldHandleEmptyCartPromotion() {
        Promotion promotion = new BuyThreePayTwoPromotion();

        PromotionResult result = promotion.apply(List.of(), List.of());

        assertEquals(0, result.getTotalToPay());
        assertEquals(0, result.gifts().size());
    }
}
