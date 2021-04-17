package ru.happyshark.java.ee.persist;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "findAllProduct", query = "from Product p"),
        @NamedQuery(name = "findAllWithCategoryFetch", query = "select p from Product p left join fetch p.category"),
        @NamedQuery(name = "countProducts", query = "select count(p) from Product p"),
        @NamedQuery(name = "findAllProductsByCategoryId", query = "select p from Product p where p.category.id = :id")
})
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}
}
