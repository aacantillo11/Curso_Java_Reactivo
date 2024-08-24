package com.alejo.carrito.controllers.product;

import com.alejo.carrito.models.product.dtos.ProductDto;
import com.alejo.carrito.models.product.dtos.ProductRequestDto;
import com.alejo.carrito.models.product.exception.ProductNotFoundException;
import com.alejo.carrito.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public Flux<ProductDto> getAllProducts(){
        return service.getAllProduct();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> getByIdProduct(@PathVariable("id") long id){
        return service.getById(id)
                .map(product -> ResponseEntity.ok(product))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping("/name/{name}")
    public Mono<ResponseEntity<ProductDto>> getByNameProduct(@PathVariable("name") String name){
        return service.getByName(name)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping
    public Mono<ResponseEntity<ProductDto>> save(@RequestBody ProductRequestDto productRequest){
        return service.save(productRequest)
                .map(product -> new ResponseEntity<>(product,HttpStatus.CREATED));
    }

    @PutMapping
    public Mono<ResponseEntity<ProductDto>> update(@RequestBody ProductDto productRequest){
        return service.update(productRequest)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") long id){
        return service.deleteById(id)
                .then( Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .onErrorResume(ProductNotFoundException.class,
                        e -> Mono.just(ResponseEntity.notFound().build()));
    }




}
