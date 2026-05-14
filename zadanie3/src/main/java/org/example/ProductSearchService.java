package org.example;

import java.util.*;

public class ProductSearchService {

    // szuka najtanszego produktu
    public Optional<Product> findCheapest(List<Product> products) {
        return cleanProducts(products).stream()
                .min(ProductComparators.priceAscendingNameAscending());
    }

    // szuka najdrozszego produktu
    public Optional<Product> findMostExpensive(List<Product> products) {
        return cleanProducts(products).stream()
                .max(Comparator.comparing(Product::price));
    }

    // szuka kilku najtanszych produktow
    public List<Product> findNCheapest(List<Product> products, int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }

        return cleanProducts(products).stream()
                .sorted(ProductComparators.priceAscendingNameAscending())
                .limit(count)
                .toList();
    }

    // szuka kilku najdrozszych produktow
    public List<Product> findNMostExpensive(List<Product> products, int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }

        return cleanProducts(products).stream()
                .sorted(ProductComparators.priceDescendingNameAscending())
                .limit(count)
                .toList();
    }

    // usuwa puste miejsca z listy
    private List<Product> cleanProducts(List<Product> products) {
        if (products == null) {
            return new ArrayList<>();
        }

        return products.stream()
                .filter(Objects::nonNull)
                .toList();
    }
}
