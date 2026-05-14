package org.example;

import java.util.Comparator;

public class ProductComparators {

    // blokuje tworzenie obiektu pomocniczego
    private ProductComparators() {
    }

    // sortuje po cenie malejaco a potem po nazwie rosnaco
    public static Comparator<Product> priceDescendingNameAscending() {
        return Comparator
                .comparing(Product::price)
                .reversed()
                .thenComparing(Product::name);
    }

    // sortuje po cenie rosnaco a potem po nazwie rosnaco
    public static Comparator<Product> priceAscendingNameAscending() {
        return Comparator
                .comparing(Product::price)
                .thenComparing(Product::name);
    }

    // sortuje po nazwie rosnaco
    public static Comparator<Product> nameAscending() {
        return Comparator.comparing(Product::name);
    }

    // sortuje po cenie po promocji malejaco
    public static Comparator<Product> discountPriceDescending() {
        return Comparator
                .comparing(Product::discountPrice)
                .reversed()
                .thenComparing(Product::name);
    }
}
