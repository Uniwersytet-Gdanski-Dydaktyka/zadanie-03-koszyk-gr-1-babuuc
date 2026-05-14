package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ProductSortService {

    // sortuje domyslnie wedlug wymagan zadania
    public List<Product> sortDefault(List<Product> products) {
        return sort(products, ProductComparators.priceDescendingNameAscending());
    }

    // sortuje wedlug podanego sposobu
    public List<Product> sort(List<Product> products, Comparator<Product> comparator) {
        if (comparator == null) {
            comparator = ProductComparators.priceDescendingNameAscending();
        }

        return cleanProducts(products).stream()
                .sorted(comparator)
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
