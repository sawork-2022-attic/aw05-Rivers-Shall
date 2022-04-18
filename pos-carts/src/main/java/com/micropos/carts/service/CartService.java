package com.micropos.carts.service;

import com.micropos.carts.model.Cart;

import java.util.List;

public interface CartService {
    public Cart findById(Long cartId);

    public List<Cart> findAll();

    public Cart changeById(Cart cart);

    public boolean deleteById(Long cartId);

    public Cart newCart();
}
