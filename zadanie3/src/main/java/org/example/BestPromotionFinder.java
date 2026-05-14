package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BestPromotionFinder {
    private final PromotionApplier promotionApplier = new PromotionApplier();

    // szuka najlepszego wyniku promocji
    public PromotionResult findBestResult(List<Product> products, List<Promotion> promotions) {
        List<Promotion> safePromotions = cleanPromotions(promotions);

        if (safePromotions.isEmpty()) {
            return PromotionResult.fromProducts(products);
        }

        List<List<Promotion>> allOrders = new ArrayList<>();
        createOrders(safePromotions, new ArrayList<>(), allOrders);

        PromotionResult bestResult = null;
        for (List<Promotion> order : allOrders) {
            PromotionResult result = promotionApplier.applyPromotions(products, order);
            if (isBetter(result, bestResult)) {
                bestResult = result;
            }
        }

        return bestResult;
    }

    // tworzy wszystkie kolejnosci promocji
    private void createOrders(List<Promotion> promotions, List<Promotion> current, List<List<Promotion>> allOrders) {
        if (promotions.isEmpty()) {
            allOrders.add(new ArrayList<>(current));
            return;
        }

        for (int index = 0; index < promotions.size(); index++) {
            Promotion promotion = promotions.get(index);
            List<Promotion> remaining = new ArrayList<>(promotions);
            remaining.remove(index);

            current.add(promotion);
            createOrders(remaining, current, allOrders);
            current.removeLast();
        }
    }

    // sprawdza czy wynik jest lepszy dla klienta
    private boolean isBetter(PromotionResult result, PromotionResult bestResult) {
        if (bestResult == null) {
            return true;
        }

        if (result.getTotalToPay() < bestResult.getTotalToPay()) {
            return true;
        }

        if (Double.compare(result.getTotalToPay(), bestResult.getTotalToPay()) == 0) {
            return result.gifts().size() > bestResult.gifts().size();
        }

        return false;
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
