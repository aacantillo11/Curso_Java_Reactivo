package com.alejo.carrito.services.orders.purchase;

import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderDto;
import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderRequestDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PurchaseOrderService {

    Flux<PurchaseOrderDto> getAllOrders();
    Mono<PurchaseOrderDto> saveOrder(PurchaseOrderRequestDto requestDto);

    Mono<PurchaseOrderDto> cancelPurchaseOrderById(long id);
}
