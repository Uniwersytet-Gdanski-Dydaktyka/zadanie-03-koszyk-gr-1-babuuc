package org.example;

import java.util.List;

public interface Promotion {

    // zwraca nazwe promocji
    String getName();

    // stosuje promocje do produktow
    PromotionResult apply(List<Product> products, List<Product> gifts);
}
