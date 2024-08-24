package com.alejo.carrito.repositories.cart;

import com.alejo.carrito.models.cart.Cart;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends R2dbcRepository<Cart,Long> {
}
