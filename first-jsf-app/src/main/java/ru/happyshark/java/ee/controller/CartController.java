package ru.happyshark.java.ee.controller;

import ru.happyshark.java.ee.service.CartService;
import ru.happyshark.java.ee.service.repr.CartRepr;
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

    public void addToCart(ProductRepr product) {
        cartService.add(product);
    }

    public void removeFromCart(Long id) { cartService.remove(id); }

    public CartRepr getCart() { return cartService.findAll(); }

    public String clearCart() {
        cartService.clear();
        return "/product.xhtml?faces-redirect=true";
    }
}
