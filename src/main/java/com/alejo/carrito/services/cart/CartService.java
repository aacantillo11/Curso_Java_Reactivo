package com.alejo.carrito.services.cart;

import com.alejo.carrito.models.cart.CartProducts;
import com.alejo.carrito.models.cart.dtos.CartDto;
import com.alejo.carrito.models.cart.dtos.CartProductRequestDto;
import com.alejo.carrito.models.cart.dtos.CartProductsDto;
import com.alejo.carrito.models.cart.dtos.CartRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartService {

    Flux<CartDto> getAllCarts();
    Mono<CartDto> createCart(CartRequestDto cart);

    Mono<CartProductsDto> addProduct(CartProductRequestDto productRequestDto);

    Mono<Void> deleteProduct(CartProductsDto cartProductsDto);

    Flux<CartProductsDto> getAllProductsCart(long carritoId);

    Mono<Void> emptyCart(long carritoId);


}
