package com.alejo.carrito.controllers.cart.converter;

import com.alejo.carrito.models.cart.Cart;
import com.alejo.carrito.models.cart.CartProducts;
import com.alejo.carrito.models.cart.dtos.CartDto;
import com.alejo.carrito.models.cart.dtos.CartProductRequestDto;
import com.alejo.carrito.models.cart.dtos.CartProductsDto;
import com.alejo.carrito.models.cart.dtos.CartRequestDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CartConverter {

    public Cart toCart(CartRequestDto dto){
        Cart cart = new Cart();
        cart.setUserId(dto.getUserId());
        cart.setCreatedAt(LocalDateTime.now());
        return cart;
    }

    public CartDto toCartDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .createdAt(cart.getCreatedAt())
                .build();
    }

    public CartProducts toCartProducts(CartProductRequestDto dto){
        CartProducts cartProducts = new CartProducts();
        cartProducts.setCarritoId(dto.getCarritoId());
        cartProducts.setProductoId(dto.getProductoId());
        cartProducts.setQuantity(dto.getQuantity());
        return cartProducts;
    }

    public CartProductsDto toCartProductsDto(CartProducts cartProducts){
        return CartProductsDto.builder()
                .id(cartProducts.getId())
                .carritoId(cartProducts.getCarritoId())
                .productoId(cartProducts.getProductoId())
                .quantity(cartProducts.getQuantity())
                .build();
    }
}
