package com.alejo.carrito.services.product;

import com.alejo.carrito.models.product.dtos.ProductDto;
import com.alejo.carrito.models.product.dtos.ProductRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Flux<ProductDto> getAllProduct();
    Mono<ProductDto> getById(long id);
    Mono<ProductDto> getByName(String name);
    Mono<ProductDto> save(ProductRequestDto dto);
    Mono<ProductDto> update(ProductDto dto);
    Mono<Void> deleteById(long id);

    Mono<Void> updateStock(long id,int quantity);
}
