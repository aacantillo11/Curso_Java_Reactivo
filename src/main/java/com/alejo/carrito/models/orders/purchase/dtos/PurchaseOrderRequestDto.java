package com.alejo.carrito.models.orders.purchase.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderRequestDto {

    private Double total;
    private List<OrderItemDto> items;

}
