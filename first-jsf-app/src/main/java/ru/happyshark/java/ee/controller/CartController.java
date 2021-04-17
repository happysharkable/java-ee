package ru.happyshark.java.ee.controller;

import ru.happyshark.java.ee.service.CartService;
import ru.happyshark.java.ee.service.repr.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public void add(ProductRepr product) {
        cartService.add(product);
    }
}
