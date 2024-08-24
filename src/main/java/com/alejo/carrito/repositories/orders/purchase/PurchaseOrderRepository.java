package com.alejo.carrito.repositories.orders.purchase;

import com.alejo.carrito.models.orders.purchase.PurchaseOrder;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PurchaseOrderRepository extends R2dbcRepository<PurchaseOrder,Long> {

    @Query("UPDATE ordenes_compras SET status = CAST(:cancel AS status_enum) WHERE id = :id")
    Mono<PurchaseOrder> updateOrder(Long id, String cancel);
}
