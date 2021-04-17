package ru.happyshark.java.ee.controller;

import ru.happyshark.java.ee.persist.Category;
import ru.happyshark.java.ee.persist.Product;
import ru.happyshark.java.ee.repository.CategoryRepository;
import ru.happyshark.java.ee.service.ProductService;
import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    private ProductRepr product;

    private List<ProductRepr> productList;

    private Category category;

    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAllWithCategoryFetch();
        this.categoryList = categoryRepository.findAll();
    }

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public List<ProductRepr> findAll() {
        return productList;
    }

    public String editProduct(ProductRepr product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductRepr product) {
        productService.delete(product.getId());
    }

    public String saveProduct() {
        productService.save(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public String addProduct() {
        this.product = new ProductRepr();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categoryList;
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/category.xhtml?faces-redirect=true";
    }

    public String addCategory() {
        this.category = new Category();
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String editCategory(Long id) {
        this.category = categoryRepository.findById(id);
        return "/category_form.xhtml?faces-redirect=true";
    }

    public String deleteCategory(Long id) {
        productService.setCategoryToNullForProductsWithCategoryId(id);
        categoryRepository.deleteById(id);
        return "/category.xhtml?faces-redirect=true";
    }
}
