package com.alejo.carrito.models.cart.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CartDto {
    private Long id;
    private Integer userId;
    private LocalDateTime createdAt;
}
