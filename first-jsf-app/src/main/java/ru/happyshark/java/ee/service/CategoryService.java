package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.CategoryRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {
    void save(CategoryRepr categoryRepr);

    void delete(Long id);

    CategoryRepr findById(long id);

    List<CategoryRepr> findAll();
}
