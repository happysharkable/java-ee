package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.Stateful;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<Long, ProductRepr> productMap = new ConcurrentHashMap<>();

    @Override
    public void add(ProductRepr productRepr) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public List<ProductRepr> findAll() {
        return null;
    }
}