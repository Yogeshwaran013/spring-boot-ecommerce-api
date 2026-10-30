package com.product.store.mappers;

import com.product.store.dtos.OrderDto;
import com.product.store.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
