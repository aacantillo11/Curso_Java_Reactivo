package com.alejo.carrito.models.orders.purchase.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseOrderDto {

    private Long id;
    private Double total;
    private String status;
    private LocalDateTime date;

}
