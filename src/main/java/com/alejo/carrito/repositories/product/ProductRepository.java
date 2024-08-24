package com.alejo.carrito.repositories.product;

import com.alejo.carrito.models.product.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends R2dbcRepository<Product,Long> {

    Mono<Product> findByName(String name);

    @Query("UPDATE productos SET stock = stock + :quantity WHERE id = :id")
    Mono<Void> updateStock(Long id, Integer quantity);
}
