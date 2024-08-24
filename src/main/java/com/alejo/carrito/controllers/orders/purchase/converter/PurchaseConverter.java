package com.alejo.carrito.controllers.orders.purchase.converter;

import com.alejo.carrito.models.orders.purchase.PurchaseOrder;
import com.alejo.carrito.models.orders.purchase.dtos.PurchaseOrderDto;
import org.springframework.stereotype.Component;

@Component
public class PurchaseConverter {


    public PurchaseOrderDto toPurchaseOrderDto(PurchaseOrder purchaseOrder){
        return PurchaseOrderDto.builder()
                .id(purchaseOrder.getId())
                .status(purchaseOrder.getStatus())
                .date(purchaseOrder.getCreatedAt())
                .total(purchaseOrder.getTotal())
                .build();
    }

    public PurchaseOrder toPurchaseOrder(PurchaseOrderDto dto){
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setTotal(dto.getTotal());
        purchaseOrder.setStatus(dto.getStatus());
        return purchaseOrder;
    }
}
