package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private final List<Promotion> promotions = new ArrayList<>();
    private final ProductSortService sortService = new ProductSortService();
    private final PromotionApplier promotionApplier = new PromotionApplier();
    private final BestPromotionFinder bestPromotionFinder = new BestPromotionFinder();
    private Comparator<Product> sortComparator = ProductComparators.priceDescendingNameAscending();

    // dodaje produkt do koszyka
    public void addProduct(Product product) {
        if (product != null) {
            products.add(product);
        }
    }

    // usuwa pierwszy produkt o podanym kodzie
    public boolean removeProductByCode(String code) {
        if (code == null) {
            return false;
        }

        return products.removeIf(product -> product.code().equals(code));
    }

    // dodaje promocje do koszyka
    public void addPromotion(Promotion promotion) {
        if (promotion != null) {
            promotions.add(promotion);
        }
    }

    // usuwa wszystkie promocje
    public void clearPromotions() {
        promotions.clear();
    }

    // zmienia sposob sortowania
    public void setSortComparator(Comparator<Product> sortComparator) {
        if (sortComparator != null) {
            this.sortComparator = sortComparator;
        }
    }

    // zwraca kopie produktow
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    // zwraca produkty posortowane
    public List<Product> getSortedProducts() {
        return sortService.sort(products, sortComparator);
    }

    // stosuje promocje w podanej kolejnosci
    public PromotionResult calculate() {
        return promotionApplier.applyPromotions(products, promotions);
    }

    // szuka najlepszej kolejnosci promocji
    public PromotionResult calculateBest() {
        return bestPromotionFinder.findBestResult(products, promotions);
    }
}
