package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record PromotionResult(List<Product> products, List<Product> gifts) {
    // tworzy wynik promocji
    public PromotionResult(List<Product> products, List<Product> gifts) {
        this.products = cleanProducts(products);
        this.gifts = cleanProducts(gifts);
    }

    // tworzy wynik bez gratisow
    public static PromotionResult fromProducts(List<Product> products) {
        return new PromotionResult(products, new ArrayList<>());
    }

    // zwraca produkty z koszyka
    @Override
    public List<Product> products() {
        return new ArrayList<>(products);
    }

    // zwraca gratisy z koszyka
    @Override
    public List<Product> gifts() {
        return new ArrayList<>(gifts);
    }

    // liczy kwote do zaplaty
    public double getTotalToPay() {
        return products.stream()
                .mapToDouble(Product::discountPrice)
                .sum();
    }

    // sprawdza czy jest gratis o kodzie
    public boolean hasGiftWithCode(String code) {
        return gifts.stream()
                .anyMatch(gift -> gift.code().equals(code));
    }

    // usuwa puste miejsca z listy
    private List<Product> cleanProducts(List<Product> list) {
        if (list == null) {
            return new ArrayList<>();
        }

        return list.stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
