package com.product.store.mappers;

import com.product.store.dtos.CartDto;
import com.product.store.dtos.CartItemDto;
import com.product.store.entities.Cart;
import com.product.store.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target="totalPrice",expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);


    @Mapping(target="totalPrice",expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
