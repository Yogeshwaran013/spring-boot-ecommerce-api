package com.product.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCartItemRequest {
    @NotNull(message = "Quality must be provided")
    @Min(value=1,message = "Quantity must be minimum 1")
    private Integer quantity;
}
