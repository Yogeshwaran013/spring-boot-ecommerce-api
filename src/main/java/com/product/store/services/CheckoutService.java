package com.product.store.services;

import com.product.store.dtos.CheckoutRequest;
import com.product.store.dtos.CheckoutResponse;
import com.product.store.dtos.ErrorDto;
import com.product.store.entities.Order;
import com.product.store.exceptions.CartEmptyException;
import com.product.store.exceptions.CartNotFoundException;
import com.product.store.repositories.CartRespository;
import com.product.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {

    private final CartRespository cartRespository;
    private final CartService cartService;
    private final AuthService authService;
    private final OrderRepository orderRepository;



    public CheckoutResponse checkout(CheckoutRequest request){
        var cart = cartRespository.getCartWithItems(request.getCartId()).orElse(null);
        if(cart==null){
            throw new CartNotFoundException();
        }
        if(cart.isEmpty()){
            throw new CartEmptyException();
        }
        var order = Order.fromCart(cart,authService.getCurrentUser());

        orderRepository.save(order);
        cartService.clearCart(cart.getId());

        return new CheckoutResponse(order.getId());
    }
}
