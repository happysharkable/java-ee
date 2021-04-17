package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.CartRepr;
import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.Local;

@Local
public interface CartService {

    void add(ProductRepr productRepr);

    void remove(long id);

    CartRepr findAll();

    void clear();
}
