package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.CartItem;
import ru.happyshark.java.ee.service.repr.CartRepr;
import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<Long, CartItem> productMap = new ConcurrentHashMap<>();

    @Override
    public void add(ProductRepr productRepr) {
        if (productMap.containsKey(productRepr.getId())) {
            productMap.get(productRepr.getId()).incrementQuantity();
        } else {
            productMap.put(productRepr.getId(), new CartItem(productRepr));
        }
    }

    @Override
    public void remove(long id) {
        productMap.remove(id);
    }

    @Override
    public CartRepr findAll() {
        return new CartRepr(new ArrayList<>(productMap.values()));
    }

    @Override
    public void clear() {
        productMap.clear();
    }
}