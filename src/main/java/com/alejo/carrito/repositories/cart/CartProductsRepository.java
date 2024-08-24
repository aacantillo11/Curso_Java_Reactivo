package com.alejo.carrito.repositories.cart;

import com.alejo.carrito.models.cart.CartProducts;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CartProductsRepository extends R2dbcRepository<CartProducts,Long> {

    Mono<CartProducts> findByCarritoIdAndProductoId(Long carritoId,Long productoId);
    Flux<CartProducts> findAllByCarritoId(Long carritoId);

    Mono<Void> deleteAllByCarritoId(Long carritoId);
}
