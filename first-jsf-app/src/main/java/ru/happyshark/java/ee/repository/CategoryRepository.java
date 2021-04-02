package ru.happyshark.java.ee.repository;

import ru.happyshark.java.ee.persist.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
@Named
public class CategoryRepository {
    private final Map<Long, Category> categoriesMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        save(new Category(null, "Game console"));
        save(new Category(null, "Game cartridge"));
        save(new Category(null, "Game CD"));
        save(new Category(null, "Other"));
    }

    public void save(Category category) {
        if (category.getId() == null) {
            category.setId(identity.incrementAndGet());
        }
        categoriesMap.put(category.getId(), category);
    }

    public void delete(Long id) {
        categoriesMap.remove(id);
    }

    public Category findById(Long id) {
        return categoriesMap.get(id);
    }

    public List<Category> findAll() {
        return new ArrayList<>(categoriesMap.values());
    }
}
