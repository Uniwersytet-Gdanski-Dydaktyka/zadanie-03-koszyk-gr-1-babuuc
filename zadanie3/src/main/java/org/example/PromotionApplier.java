package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PromotionApplier {

    // stosuje promocje po kolei
    public PromotionResult applyPromotions(List<Product> products, List<Promotion> promotions) {
        PromotionResult result = PromotionResult.fromProducts(products);

        for (Promotion promotion : cleanPromotions(promotions)) {
            result = promotion.apply(result.products(), result.gifts());
        }

        return result;
    }

    // usuwa puste miejsca z listy promocji
    private List<Promotion> cleanPromotions(List<Promotion> promotions) {
        if (promotions == null) {
            return new ArrayList<>();
        }

        return promotions.stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
