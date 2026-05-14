package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MugGiftPromotion implements Promotion {
    public static final String MUG_CODE = "MUG";

    private final double minimumValue;
    private final CartCalculator calculator = new CartCalculator();

    // tworzy promocje z kubkiem gratis
    public MugGiftPromotion(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    // zwraca nazwe promocji
    @Override
    public String getName() {
        return "mug gift";
    }

    // dodaje kubek gdy suma przekracza prog
    @Override
    public PromotionResult apply(List<Product> products, List<Product> gifts) {
        List<Product> safeProducts = copyProducts(products);
        List<Product> safeGifts = new ArrayList<>(copyProducts(gifts));

        if (calculator.sumDiscountPrices(safeProducts) <= minimumValue) {
            return new PromotionResult(safeProducts, safeGifts);
        }

        boolean mugAlreadyAdded = safeGifts.stream()
                .anyMatch(gift -> gift.code().equals(MUG_CODE));

        if (!mugAlreadyAdded) {
            safeGifts.add(new Product(MUG_CODE, "firmowy kubek", 0));
        }

        return new PromotionResult(safeProducts, safeGifts);
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
