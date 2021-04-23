package ru.happyshark.java.ee.controller;

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

    private ProductRepr product;

    private List<ProductRepr> productList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAllWithCategoryFetch();
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
}
