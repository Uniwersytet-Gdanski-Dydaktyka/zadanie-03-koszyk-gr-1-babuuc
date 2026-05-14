package org.example;

import java.util.List;
import java.util.Objects;

public class CartCalculator {

    // liczy sume cen przed promocja
    public double sumPrices(List<Product> products) {
        if (products == null) {
            return 0;
        }

        return products.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Product::price)
                .sum();
    }

    // liczy sume cen po promocji
    public double sumDiscountPrices(List<Product> products) {
        if (products == null) {
            return 0;
        }

        return products.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Product::discountPrice)
                .sum();
    }
}
