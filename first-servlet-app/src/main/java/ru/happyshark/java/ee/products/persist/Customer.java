package ru.happyshark.java.ee.products.persist;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;

    public Customer() {}
}
