package ru.happyshark.java.ee.service.repr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryRepr {
    Long id;
    String name;

    public CategoryRepr() {

    }
}
