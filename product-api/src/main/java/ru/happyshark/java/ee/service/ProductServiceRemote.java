package ru.happyshark.java.ee.service;

import ru.happyshark.java.ee.service.repr.ProductRepr;

import java.util.List;

public interface ProductServiceRemote {

    List<ProductRepr> findAllRemote();
}
