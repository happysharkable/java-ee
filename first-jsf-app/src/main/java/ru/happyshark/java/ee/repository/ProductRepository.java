package ru.happyshark.java.ee.repository;

import ru.happyshark.java.ee.persist.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void delete(Long id) {
        em.createNamedQuery("deleteProductById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAllProduct", Product.class)
                .getResultList();
    }

    public List<Product> findAllWithCategoryFetch() {
        return em.createNamedQuery("findAllWithCategoryFetch", Product.class)
                .getResultList();
    }

    public void setCategoryToNullForProductsWithCategoryId(Long categoryId) {
        List<Product> products = em.createNamedQuery("findAllProductsByCategoryId", Product.class)
                .setParameter("id", categoryId)
                .getResultList();
        for (Product p : products) {
            p.setCategory(null);
            save(p);
        }
    }

    public long count() {
        return em.createNamedQuery("countProducts", Long.class).getSingleResult();
    }
}