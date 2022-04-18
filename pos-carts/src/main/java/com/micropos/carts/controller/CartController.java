package com.micropos.carts.controller;

import com.micropos.carts.api.CartsApi;
import com.micropos.carts.dto.CartDto;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class CartController implements CartsApi {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Override
    public ResponseEntity<Void> deleteCartById(Long cartId) {
        if (cartService.deleteById(cartId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<CartDto> mutateCartById(Long cartId, CartDto cartDto) {
        Cart cartRet = cartService.changeById(cartMapper.toCart(cartDto));
        if (cartRet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        CartDto cartDtoRet = cartMapper.toCartDto(cartRet);
        return new ResponseEntity<>(cartDtoRet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> newCart() {
        CartDto newCartDto = cartMapper.toCartDto(cartService.newCart());
        return new ResponseEntity<>(newCartDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CartDto> showCartById(Long cartId) {
        CartDto cartDto = cartMapper.toCartDto(cartService.findById(cartId));
        if (cartDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }
}
