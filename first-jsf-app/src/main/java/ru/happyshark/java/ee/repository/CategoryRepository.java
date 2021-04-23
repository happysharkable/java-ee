package ru.happyshark.java.ee.repository;

import ru.happyshark.java.ee.persist.Category;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategories", Category.class)
                .getResultList();
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategories", Long.class)
                .getSingleResult();
    }

    @TransactionAttribute
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @TransactionAttribute
    public void deleteById(Long id) {
        em.createQuery("UPDATE Product p SET p.category = null WHERE p.category.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.createNamedQuery("deleteCategoryById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
