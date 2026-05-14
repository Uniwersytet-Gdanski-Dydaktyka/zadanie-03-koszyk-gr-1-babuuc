package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class BuyThreePayTwoPromotion implements Promotion {

    // zwraca nazwe promocji
    @Override
    public String getName() {
        return "buy three pay two";
    }

    // zeruje cene najtanszych produktow
    @Override
    public PromotionResult apply(List<Product> products, List<Product> gifts) {
        List<Product> safeProducts = copyProducts(products);
        List<Product> safeGifts = copyProducts(gifts);

        int freeCount = safeProducts.size() / 3;
        if (freeCount == 0) {
            return new PromotionResult(safeProducts, safeGifts);
        }

        List<Integer> freeIndexes = findFreeIndexes(safeProducts, freeCount);
        List<Product> changedProducts = new ArrayList<>();

        for (int index = 0; index < safeProducts.size(); index++) {
            Product product = safeProducts.get(index);
            if (freeIndexes.contains(index)) {
                changedProducts.add(product.withDiscountPrice(0));
            } else {
                changedProducts.add(product);
            }
        }

        return new PromotionResult(changedProducts, safeGifts);
    }

    // znajduje indeksy produktow gratisowych
    private List<Integer> findFreeIndexes(List<Product> products, int freeCount) {
        List<Integer> indexes = new ArrayList<>();
        for (int index = 0; index < products.size(); index++) {
            indexes.add(index);
        }

        indexes.sort(Comparator
                .comparing((Integer index) -> products.get(index).discountPrice())
                .thenComparing(index -> products.get(index).name()));

        return indexes.stream()
                .limit(freeCount)
                .toList();
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
