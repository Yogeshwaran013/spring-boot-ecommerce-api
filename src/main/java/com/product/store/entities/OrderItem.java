package com.product.store.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id",nullable = false)
    private Product product;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name="quantity")
    private Integer quanity ;

    @Column(name="total_price")
    private BigDecimal totalPrice;
}
