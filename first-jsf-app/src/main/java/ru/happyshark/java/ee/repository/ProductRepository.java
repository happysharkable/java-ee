package ru.happyshark.java.ee.repository;

import ru.happyshark.java.ee.persist.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        save(new Product(null, "Nintendo Switch", "Game Console", new BigDecimal(17000)));
        save(new Product(null, "Zelda Breath of The Wild", "Open world game", new BigDecimal(4700)));
        save(new Product(null, "Final Fantasy X", "JRPG", new BigDecimal(4500)));
        save(new Product(null, "Sony PS5", "Big white thing", new BigDecimal(50000)));
    }

    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
    }

    public void delete(Long id) {
        productMap.remove(id);
    }

    public Product findById(Long id) {
        return productMap.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
}