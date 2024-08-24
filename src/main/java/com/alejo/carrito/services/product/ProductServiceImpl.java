package com.alejo.carrito.services.product;

import com.alejo.carrito.controllers.product.converter.ProductConverter;
import com.alejo.carrito.models.product.Product;
import com.alejo.carrito.models.product.dtos.ProductDto;
import com.alejo.carrito.models.product.dtos.ProductRequestDto;
import com.alejo.carrito.models.product.exception.ProductNotFoundException;
import com.alejo.carrito.repositories.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;
    private final ProductConverter converter;

    @Override
    public Flux<ProductDto> getAllProduct() {
        return repository.findAll()
                .map(product -> converter.toProductDto(product));
    }

    @Override
    public Mono<ProductDto> getById(long id) {
        return repository.findById(id)
                .map(converter::toProductDto);
    }

    @Override
    public Mono<ProductDto> getByName(String name) {
        return repository.findByName(name)
                .map(converter::toProductDto);
    }

    @Override
    public Mono<ProductDto> save(ProductRequestDto dto) {
        Product product = converter.toProduct(dto);
        return repository.save(product)
                .map(converter::toProductDto);
    }

    @Override
    public Mono<ProductDto> update(ProductDto dto) {

        return repository.findById(dto.getId())
                .flatMap(product -> {
                    product.setName(dto.getName());
                    product.setDescription(dto.getDescription());
                    product.setImageUrl(dto.getImageUrl());
                    product.setPrice(dto.getPrice());
                    product.setStock(dto.getStock());
                    return repository.save(product);
                }).map(converter::toProductDto);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("El producto no existe")))
                .flatMap(product -> repository.deleteById(product.getId()));
    }

    @Override
    public Mono<Void> updateStock(long id, int quantity) {
        return repository.updateStock(id,quantity);
    }

}
