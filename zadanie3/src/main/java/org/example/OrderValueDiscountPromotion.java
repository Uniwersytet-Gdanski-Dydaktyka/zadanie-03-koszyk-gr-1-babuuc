package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderValueDiscountPromotion implements Promotion {
    private final double minimumValue;
    private final double discountPercent;
    private final CartCalculator calculator = new CartCalculator();

    // tworzy promocje z progiem i procentem
    public OrderValueDiscountPromotion(double minimumValue, double discountPercent) {
        this.minimumValue = minimumValue;
        this.discountPercent = discountPercent;
    }

    // zwraca nazwe promocji
    @Override
    public String getName() {
        return "order value discount";
    }

    // obniza cene gdy suma przekracza prog
    @Override
    public PromotionResult apply(List<Product> products, List<Product> gifts) {
        List<Product> safeProducts = copyProducts(products);
        List<Product> safeGifts = copyProducts(gifts);

        if (calculator.sumDiscountPrices(safeProducts) <= minimumValue) {
            return new PromotionResult(safeProducts, safeGifts);
        }

        List<Product> changedProducts = new ArrayList<>();
        for (Product product : safeProducts) {
            double newPrice = product.discountPrice() * (1 - discountPercent);
            changedProducts.add(product.withDiscountPrice(newPrice));
        }

        return new PromotionResult(changedProducts, safeGifts);
    }

    // kopiuje produkty bez pustych miejsc
    private List<Product> copyProducts(List<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        return products.stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
