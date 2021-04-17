package ru.happyshark.java.ee.service.repr;

import java.math.BigDecimal;
import java.util.List;

public class CartRepr {
    private final List<CartItem> items;
    private BigDecimal price;

    public CartRepr(List<CartItem> items) {
        this.items = items;
        recalculatePrice();
    }

    private void recalculatePrice() {
        price = new BigDecimal(0);
        for (CartItem c : items) {
            price = price.add(c.getProduct().getPrice().multiply(new BigDecimal(c.getQuantity())));
        }
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
