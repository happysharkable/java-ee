package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.persist.Category;
import ru.happyshark.java.ee.repository.CategoryRepository;
import ru.happyshark.java.ee.rest.CategoryResource;
import ru.happyshark.java.ee.service.repr.CategoryRepr;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryResource {
    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public void insert(CategoryRepr categoryRepr) {
        if (categoryRepr.getId() != null) {
            throw new RuntimeException("Category Id field is not null!");
        }
        save(categoryRepr);
    }

    @Override
    public void save(CategoryRepr categoryRepr) {
        categoryRepository.saveOrUpdate(new Category(null, categoryRepr.getName()));
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryRepr findById(long id) {
        return createCategoryReprFromCategory(categoryRepository.findById(id));
    }

    @Override
    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream()
                .map(this::createCategoryReprFromCategory).collect(Collectors.toList());

    }

    private CategoryRepr createCategoryReprFromCategory(Category category) {
        return new CategoryRepr(category.getId(), category.getName());
    }
}
