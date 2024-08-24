package com.alejo.carrito.services.cart;

import com.alejo.carrito.controllers.cart.converter.CartConverter;
import com.alejo.carrito.models.cart.dtos.CartDto;
import com.alejo.carrito.models.cart.dtos.CartProductRequestDto;
import com.alejo.carrito.models.cart.dtos.CartProductsDto;
import com.alejo.carrito.models.cart.dtos.CartRequestDto;
import com.alejo.carrito.repositories.cart.CartProductsRepository;
import com.alejo.carrito.repositories.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository repository;
    private final CartProductsRepository cartProductsRepository;
    private final CartConverter converter;

    @Override
    public Flux<CartDto> getAllCarts() {
        return repository.findAll()
                .map(converter::toCartDto);
    }

    @Override
    public Mono<CartDto> createCart(CartRequestDto cart) {
        return repository.save(converter.toCart(cart))
                .map(converter::toCartDto);
    }

    @Override
    public Mono<CartProductsDto> addProduct(CartProductRequestDto productRequestDto) {
        return cartProductsRepository.save(converter.toCartProducts(productRequestDto))
                .map(converter::toCartProductsDto);
    }

    @Override
    public Mono<Void> deleteProduct(CartProductsDto cartProductsDto) {
        return cartProductsRepository
                .findByCarritoIdAndProductoId(cartProductsDto.getCarritoId(), cartProductsDto.getProductoId())
                .flatMap(cartProducts -> cartProductsRepository.delete(cartProducts));
    }

    @Override
    public Flux<CartProductsDto> getAllProductsCart(long carritoId) {
        return cartProductsRepository.findAllByCarritoId(carritoId)
                .map(converter::toCartProductsDto);
    }

    @Override
    public Mono<Void> emptyCart(long carritoId) {
        return cartProductsRepository.deleteAllByCarritoId(carritoId);
    }
}
