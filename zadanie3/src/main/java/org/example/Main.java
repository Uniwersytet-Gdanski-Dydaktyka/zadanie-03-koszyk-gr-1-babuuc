package org.example;

public class Main {

    // uruchamia prosty przyklad programu
    static void main() {
        Cart cart = new Cart();

        cart.addProduct(new Product("P1", "monitor", 500));
        cart.addProduct(new Product("P2", "mysz", 50));
        cart.addProduct(new Product("P3", "klawiatura", 150));

        cart.addPromotion(new OrderValueDiscountPromotion(300, 0.05));
        cart.addPromotion(new BuyThreePayTwoPromotion());
        cart.addPromotion(new MugGiftPromotion(200));
        cart.addPromotion(new ProductCouponPromotion("P1", 0.30));

        PromotionResult result = cart.calculateBest();

        System.out.println("do zaplaty " + result.getTotalToPay());
        System.out.println("liczba gratisow " + result.gifts().size());
    }
}
