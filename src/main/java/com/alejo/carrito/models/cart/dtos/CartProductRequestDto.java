package com.alejo.carrito.models.cart.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProductRequestDto {
    private Long carritoId;
    private Long productoId;
    private Integer quantity;
}
