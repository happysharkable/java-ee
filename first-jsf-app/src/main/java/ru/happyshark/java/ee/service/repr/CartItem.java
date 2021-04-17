package ru.happyshark.java.ee.service.repr;

public class CartItem {
    private final ProductRepr product;
    private int quantity;

    public CartItem(ProductRepr product) {
        this.product = product;
        incrementQuantity();
    }

    public ProductRepr getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() { quantity++; }
}