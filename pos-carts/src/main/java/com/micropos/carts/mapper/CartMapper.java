package com.micropos.carts.mapper;

import com.micropos.carts.dto.CartDto;
import com.micropos.carts.model.Cart;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface CartMapper {

    Collection<CartDto> toCartsDto(Collection<CartDto> carts);

    Collection<CartDto> toCarts(Collection<CartDto> carts);

    Cart toCart(CartDto cartDto);

    CartDto toCartDto(Cart cart);
}
