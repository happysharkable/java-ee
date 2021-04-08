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
        @NamedQuery(name = "count", query = "select count(p) from Product p")
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

    public Product() {}
}
