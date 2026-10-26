package com.product.store.services;

import com.product.store.dtos.CartDto;
import com.product.store.dtos.CartItemDto;
import com.product.store.entities.Cart;
import com.product.store.exceptions.CartNotFoundException;
import com.product.store.exceptions.ProductNotFoundException;
import com.product.store.mappers.CartMapper;
import com.product.store.repositories.CartRespository;
import com.product.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@AllArgsConstructor
@Service
public class CartService {

    private final CartRespository cartRespository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;
    public CartDto createCart(){
        var cart = new Cart();
        cartRespository.save(cart);
        return cartMapper.toDto(cart);

    }

    public CartItemDto addToCart(UUID cartId, Long productId){
        var cart = cartRespository.getCartWithItems(cartId).orElse(null);
        if(cart==null){
            throw new CartNotFoundException();
        }
        var product = productRepository.findById(productId).orElse(null);

        if(product==null){
            throw new ProductNotFoundException();
        }

        var cartItem = cart.addItem(product);

        cartRespository.save(cart);

        return cartMapper.toDto(cartItem);
    }

    public CartDto getCart(UUID cartId){
        var cart = cartRespository.getCartWithItems(cartId).orElse(null);
        if(cart==null){
            throw new CartNotFoundException();
        }
        return cartMapper.toDto(cart);

    }

    public CartItemDto updateItem(UUID cartId, Long productId, Integer quantity){
        var cart = cartRespository.getCartWithItems(cartId).orElse(null);
        if(cart==null){
            throw new CartNotFoundException();
        }
        var cartItem = cart.getItem(productId);
        if(cartItem==null){
           throw new ProductNotFoundException();
        }
        cartItem.setQuantity(quantity);
        cartRespository.save(cart);
        return cartMapper.toDto(cartItem);

    }

    public void removeItem(UUID cartId,Long productId){
        var cart = cartRespository.getCartWithItems(cartId).orElse(null);
        if(cart==null){
            throw new CartNotFoundException();
        }
        cart.removeItem(productId);

        cartRespository.save(cart);
    }


    public void clearCart(UUID cartId){
        var cart = cartRespository.getCartWithItems(cartId).orElse(null);
        if(cart == null){
            throw new CartNotFoundException();
        }
        cart.clear();
        cartRespository.save(cart);
    }


}
