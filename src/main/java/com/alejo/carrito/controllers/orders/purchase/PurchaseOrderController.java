package com.alejo.carrito.controllers.orders.purchase;


import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderDto;
import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderRequestDto;
import com.alejo.carrito.services.orders.purchase.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService service;

    @GetMapping
    public Flux<PurchaseOrderDto> getAll(){
        return service.getAllOrders();
    }
    @DeleteMapping("/{id}")
    public Mono<PurchaseOrderDto> cancelPurchaseOrderById(@PathVariable("id") Long id){
        return service.cancelPurchaseOrderById(id);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PurchaseOrderDto> getAllPurchaseOrders(@RequestBody PurchaseOrderRequestDto requestDto){
        return service.saveOrder(requestDto);
    }
}
