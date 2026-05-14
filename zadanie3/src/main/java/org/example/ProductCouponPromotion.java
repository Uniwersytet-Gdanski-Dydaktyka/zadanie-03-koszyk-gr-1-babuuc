package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductCouponPromotion implements Promotion {
    private final String productCode;
    private final double discountPercent;

    // tworzy kupon dla jednego produktu
    public ProductCouponPromotion(String productCode, double discountPercent) {
        if (productCode == null) {
            throw new IllegalArgumentException("product code cannot be null");
        }
        this.productCode = productCode;
        this.discountPercent = discountPercent;
    }

    // zwraca nazwe promocji
    @Override
    public String getName() {
        return "product coupon";
    }

    // obniza cene jednego wybranego produktu
    @Override
    public PromotionResult apply(List<Product> products, List<Product> gifts) {
        List<Product> safeProducts = copyProducts(products);
        List<Product> safeGifts = copyProducts(gifts);
        List<Product> changedProducts = new ArrayList<>();
        boolean used = false;

        for (Product product : safeProducts) {
            if (!used && product.code().equals(productCode)) {
                double newPrice = product.discountPrice() * (1 - discountPercent);
                changedProducts.add(product.withDiscountPrice(newPrice));
                used = true;
            } else {
                changedProducts.add(product);
            }
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
