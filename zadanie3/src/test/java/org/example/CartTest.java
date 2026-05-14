package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartTest {

    // sprawdza dodawanie produktu
    @Test
    void shouldAddProductToCart() {
        Cart cart = new Cart();

        cart.addProduct(new Product("P1", "monitor", 500));

        assertEquals(1, cart.getProducts().size());
    }

    // sprawdza usuwanie produktu
    @Test
    void shouldRemoveProductByCode() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "monitor", 500));

        boolean removed = cart.removeProductByCode("P1");

        assertTrue(removed);
        assertEquals(0, cart.getProducts().size());
    }

    // sprawdza sortowanie w koszyku
    @Test
    void shouldReturnSortedProducts() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "mysz", 50));
        cart.addProduct(new Product("P2", "monitor", 500));

        assertEquals("monitor", cart.getSortedProducts().get(0).name());
    }

    // sprawdza zmiane sortowania w koszyku
    @Test
    void shouldChangeSortingInCart() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "mysz", 50));
        cart.addProduct(new Product("P2", "monitor", 500));
        cart.setSortComparator(ProductComparators.nameAscending());

        assertEquals("monitor", cart.getSortedProducts().get(0).name());
    }

    // sprawdza liczenie promocji w koszyku
    @Test
    void shouldCalculatePromotions() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "monitor", 500));
        cart.addPromotion(new OrderValueDiscountPromotion(300, 0.05));

        PromotionResult result = cart.calculate();

        assertEquals(475, result.getTotalToPay());
    }

    // sprawdza najlepsze promocje w koszyku
    @Test
    void shouldCalculateBestPromotions() {
        Cart cart = new Cart();
        cart.addProduct(new Product("P1", "monitor", 500));
        cart.addProduct(new Product("P2", "mysz", 50));
        cart.addProduct(new Product("P3", "klawiatura", 150));
        cart.addPromotion(new OrderValueDiscountPromotion(300, 0.05));
        cart.addPromotion(new BuyThreePayTwoPromotion());
        cart.addPromotion(new ProductCouponPromotion("P1", 0.30));

        PromotionResult result = cart.calculateBest();

        assertEquals(475, result.getTotalToPay(), 0.001);
    }
}
