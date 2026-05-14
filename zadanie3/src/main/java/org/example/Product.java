package org.example;

public record Product(String code, String name, double price, double discountPrice) {
    // tworzy produkt bez promocji
    public Product(String code, String name, double price) {
        this(code, name, price, price);
    }

    // tworzy produkt z cena po promocji
    public Product {
        if (code == null) {
            throw new IllegalArgumentException("code cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        if (discountPrice < 0) {
            throw new IllegalArgumentException("discount price cannot be negative");
        }

    }

    // tworzy kopie z nowa cena po promocji
    public Product withDiscountPrice(double newDiscountPrice) {
        return new Product(code, name, price, newDiscountPrice);
    }

    // porownuje produkty po polach
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Product(String code1, String name1, double price1, double discountPrice1))) {
            return false;
        }
        return Double.compare(price1, price) == 0
                && Double.compare(discountPrice1, discountPrice) == 0
                && code.equals(code1)
                && name.equals(name1);
    }

    // tworzy tekst z produktem
    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
