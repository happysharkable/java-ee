package ru.happyshark.java.ee.controller;

import ru.happyshark.java.ee.service.CategoryService;
import ru.happyshark.java.ee.service.repr.CategoryRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @EJB
    private CategoryService categoryService;

    private CategoryRepr category;

    private List<CategoryRepr> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.categoryList = categoryService.findAll();
    }

    public CategoryRepr getCategory() {
        return category;
    }

    public void setCategory(CategoryRepr category) {
        this.category = category;
    }

    public List<CategoryRepr> getCategories() {
        return categoryList;
    }

    public String saveCategory() {
        categoryService.save(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new CategoryRepr();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String editCategory(Long id) {
        this.category = categoryService.findById(id);
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String deleteCategory(Long id) {
        categoryService.delete(id);
        return "/category.xhtml?faces-redirect=true";
    }
}
