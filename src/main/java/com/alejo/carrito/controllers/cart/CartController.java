package com.alejo.carrito.controllers.cart;

import com.alejo.carrito.models.cart.dtos.CartDto;
import com.alejo.carrito.models.cart.dtos.CartProductRequestDto;
import com.alejo.carrito.models.cart.dtos.CartProductsDto;
import com.alejo.carrito.models.cart.dtos.CartRequestDto;
import com.alejo.carrito.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @GetMapping
    public Flux<CartDto> getAllCarts(){
        return service.getAllCarts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CartDto> createCart(@RequestBody CartRequestDto cart){
        return service.createCart(cart);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CartProductsDto> addProduct(@RequestBody CartProductRequestDto cartProductRequestDto){
        return service.addProduct(cartProductRequestDto);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@RequestBody CartProductsDto cartProductsDto){
        return service.deleteProduct(cartProductsDto);
    }

    @GetMapping("/{id}")
    public  Flux<CartProductsDto> getAllProductsCart(@PathVariable("id") Long id){
        return service.getAllProductsCart(id);
    }

    @DeleteMapping("/empty-cart/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> emptyCart(@PathVariable("id") Long id){
        return service.emptyCart(id);
    }


}
