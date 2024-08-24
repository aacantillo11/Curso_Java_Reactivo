package com.alejo.carrito.repositories.orders.purchase;

import com.alejo.carrito.models.orders.purchase.PurchaseOrderProduct;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderProductRepository extends R2dbcRepository<PurchaseOrderProduct,Long> {
}
