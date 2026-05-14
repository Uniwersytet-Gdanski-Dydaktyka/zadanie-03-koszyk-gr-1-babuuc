package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {

    // sprawdza czy produkt zapisuje dane
    @Test
    void shouldCreateProduct() {
        Product product = new Product("P1", "monitor", 100);

        assertEquals("P1", product.code());
        assertEquals("monitor", product.name());
        assertEquals(100, product.price());
        assertEquals(100, product.discountPrice());
    }

    // sprawdza kopie z nowa cena
    @Test
    void shouldCreateCopyWithDiscountPrice() {
        Product product = new Product("P1", "monitor", 100);

        Product changedProduct = product.withDiscountPrice(80);

        assertEquals(100, product.discountPrice());
        assertEquals(80, changedProduct.discountPrice());
    }

    // sprawdza blad dla pustego kodu
    @Test
    void shouldThrowWhenCodeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, "monitor", 100));
    }

    // sprawdza blad dla ujemnej ceny
    @Test
    void shouldThrowWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Product("P1", "monitor", -1));
    }
}
