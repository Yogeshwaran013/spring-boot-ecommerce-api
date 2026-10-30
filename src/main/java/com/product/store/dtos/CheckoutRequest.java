package com.product.store.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class CheckoutRequest {

    private UUID cartId;
}
