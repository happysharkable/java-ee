package ru.happyshark.java.ee.products.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        products.put(product.getId(), product);
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}
