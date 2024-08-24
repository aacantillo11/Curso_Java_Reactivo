package com.alejo.carrito.models.orders.purchase.dtos;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;
    private Integer quantity;
    private Double price;

}
