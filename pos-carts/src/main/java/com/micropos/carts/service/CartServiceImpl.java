package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findById(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        return cartOptional.orElse(null);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart changeById(Cart cart) {
        if (cartRepository.findById(cart.getCartId()).isEmpty()) {
            return null;
        }
        return cartRepository.save(cart);
    }

    @Override
    public boolean deleteById(Long cartId) {
        cartRepository.deleteById(cartId);
        return true;
    }

    @Override
    public Cart newCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }
}
