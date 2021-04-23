package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    void save(ProductRepr product);

    void delete(Long id);

    ProductRepr findById(long id);

    List<ProductRepr> findAll();

    List<ProductRepr> findAllWithCategoryFetch();

    List<ProductRepr> findAllByCategoryId(long id);

    long count();
}
