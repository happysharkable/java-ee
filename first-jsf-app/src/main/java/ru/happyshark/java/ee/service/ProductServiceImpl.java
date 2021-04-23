package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.persist.Product;
import ru.happyshark.java.ee.repository.CategoryRepository;
import ru.happyshark.java.ee.repository.ProductRepository;
import ru.happyshark.java.ee.rest.ProductResource;
import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductResource {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    @TransactionAttribute
    public void save(ProductRepr productRepr) {
        productRepository.save(new Product(productRepr.getId(),
                productRepr.getName(),
                productRepr.getDescription(),
                productRepr.getPrice(),
                categoryRepository.getReference(productRepr.getCategoryId())
        ));
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductRepr findById(long id) {
        return createProductReprWithCategory(productRepository.findById(id));
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductServiceImpl::createProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findAllWithCategoryFetch() {
        return productRepository.findAllWithCategoryFetch().stream()
                .map(ProductServiceImpl::createProductReprWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    private static ProductRepr createProductReprWithCategory(Product product) {
        return new ProductRepr(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null);
    }

    private static ProductRepr createProductRepr(Product product) {
        return new ProductRepr(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                null,
                null);
    }

    @Override
    public List<ProductRepr> findAllRemote() {
        return findAllWithCategoryFetch();
    }

    @Override
    public void insert(ProductRepr productRepr) {
        if (productRepr.getId() != null) {
            throw new IllegalArgumentException("Not null id in the inserted Product");
        }
        save(productRepr);
    }

    @Override
    public void update(ProductRepr productRepr) {
        if (productRepr.getId() == null) {
            throw new IllegalArgumentException("Null id in the inserted Product");
        }
        save(productRepr);
    }

    @Override
    public List<ProductRepr> findAllByCategoryId(long id) {
        return productRepository.findAllByCategoryId(id).stream()
                .map(ProductServiceImpl::createProductReprWithCategory).collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> findAllByProductName(String name) {
        return productRepository.findAllByProductName(name).stream()
                .map(ProductServiceImpl::createProductReprWithCategory).collect(Collectors.toList());
    }
}
