package com.alejo.carrito.models.product.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequestDto {
    private String name;
    private Double price;
    private String description;
    private String imageUrl;
    private Integer stock;
}
