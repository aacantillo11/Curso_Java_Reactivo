package com.alejo.carrito.services.orders.purchase;

import com.alejo.carrito.controllers.orders.purchase.converter.PurchaseConverter;
import com.alejo.carrito.models.orders.purchase.PurchaseOrder;
import com.alejo.carrito.models.orders.purchase.PurchaseOrderProduct;
import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderDto;
import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderRequestDto;
import com.alejo.carrito.repositories.orders.purchase.PurchaseOrderProductRepository;
import com.alejo.carrito.repositories.orders.purchase.PurchaseOrderRepository;
import com.alejo.carrito.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderProductRepository purchaseOrderProductRepository;

    private final ProductService productService;
    private final PurchaseConverter converter;

    @Override
    public Flux<PurchaseOrderDto> getAllOrders() {
        return purchaseOrderRepository.findAll()
                .map(converter::toPurchaseOrderDto);
    }

    @Override
    public Mono<PurchaseOrderDto> saveOrder(PurchaseOrderRequestDto requestDto) {

        PurchaseOrderDto purchaseOrderDto = PurchaseOrderDto.builder()
                .total(requestDto.getTotal())
                .build();

        PurchaseOrder purchaseOrder = converter.toPurchaseOrder(purchaseOrderDto);
        Mono<PurchaseOrder> purchaseOrderSave = purchaseOrderRepository.save(purchaseOrder);

        //registerProductInOrder(purchaseOrderSave,requestDto).subscribe();

        //return purchaseOrderSave.map(converter::toPurchaseOrderDto);

       /* return purchaseOrderSave
                .flatMap(order -> registerProductInOrder(Mono.just(order), requestDto)
                        .then(Mono.just(order)))
                .map(converter::toPurchaseOrderDto);*/
         return purchaseOrderSave
                 .flatMap(order -> registerProductInOrder(Mono.just(order), requestDto)
                         .flatMap(purchaseOrderProduct -> productService.updateStock(purchaseOrderProduct.getProductoId(), purchaseOrderProduct.getQuantity() ))
                         .then(Mono.just(order)))
                 .map(converter::toPurchaseOrderDto);


    }

    @Override
    public Mono<PurchaseOrderDto> cancelPurchaseOrderById(long id) {

        /*return purchaseOrderRepository.findById(id)
                .map(purchaseOrder -> {
                    System.out.println(purchaseOrder.getCreatedAt());
                    System.out.println(purchaseOrder.getCreatedAt().plusMinutes(30));
                    System.out.println("Hoy " + LocalDateTime.now().isBefore(purchaseOrder.getCreatedAt().plusMinutes(30)));
                    System.out.println("Resta: " + LocalDateTime.now().isAfter(purchaseOrder.getCreatedAt()) );

                    return converter.toPurchaseOrderDto(purchaseOrder);
                });*/
        return purchaseOrderRepository.findById(id)
                .flatMap( purchaseOrder -> {
                    System.out.println(LocalDateTime.now().isBefore(purchaseOrder.getCreatedAt().plusMinutes(30)));
                 if (LocalDateTime.now().isBefore(purchaseOrder.getCreatedAt().plusMinutes(30))){
                    return purchaseOrderRepository.updateOrder(id,"Cancelada");
                 }else {
                     return Mono.empty();
                 }
                }).map(converter::toPurchaseOrderDto);
    }

    private Flux<PurchaseOrderProduct> registerProductInOrder(Mono<PurchaseOrder> purchase
            , PurchaseOrderRequestDto requestDto){

        return  purchase.flatMapMany( order -> {
            List<PurchaseOrderProduct> products = requestDto.getItems().stream().map( item -> {
                PurchaseOrderProduct purchaseOrderProduct = new PurchaseOrderProduct();
                purchaseOrderProduct.setOrdenCompraId(order.getId());
                purchaseOrderProduct.setPrice(item.getPrice());
                purchaseOrderProduct.setQuantity(item.getQuantity());
                purchaseOrderProduct.setProductoId(item.getId());
                productService.updateStock(item.getId(), item.getQuantity());
                return purchaseOrderProduct;
            }).collect(Collectors.toList());

            return purchaseOrderProductRepository.saveAll(products);
        });

    }

}
