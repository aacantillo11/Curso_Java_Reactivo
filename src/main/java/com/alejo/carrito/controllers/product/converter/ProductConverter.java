package com.alejo.carrito.controllers.product.converter;

import com.alejo.carrito.models.product.Product;
import com.alejo.carrito.models.product.dtos.ProductDto;
import com.alejo.carrito.models.product.dtos.ProductRequestDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toProduct(ProductRequestDto dto){
        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        return product;
    }

    public Product toProduct(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setDescription(dto.getDescription());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        return product;
    }

    public ProductDto toProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock()).build();
    }
}
